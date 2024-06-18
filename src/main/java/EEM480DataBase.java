/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Erkan_Kaya_HW3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eerka
 */
class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(String message) {
        super(message);
    }
}

public class EEM480DataBase implements DB_Interface, Iterable {

    private List<LinkedList<Item>> items;
    private List<Customer> customers;
    private int count;

    public EEM480DataBase() {
        items = new ArrayList<>();
        customers = new ArrayList<>();
        count = 0;
    }

    @Override
    public Iterator<Customer> iterator() {
        return new Iterator<Customer>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count && customers.get(currentIndex) != null;
            }

            @Override
            public Customer next() {
                return customers.get(currentIndex++);
            }
        };
    }

    @Override
    public void addCustomer(Customer NCustomer) {
        if (!customers.contains(NCustomer)) {
            count++;
            customers.add(NCustomer);
            LinkedList<Item> itemsOfCustomer = new LinkedList<>();
            itemsOfCustomer.add(new Item("", "", 0, null));
            items.add(itemsOfCustomer);
        }
    }

    @Override
    public Customer getNewCustomer(String NameOfCustomer, String SurnameOfCustomer, int IDOfCustomer) {
        return new Customer(NameOfCustomer, SurnameOfCustomer, IDOfCustomer, null);
    }

    @Override
    public void addNewItem(Integer IDOfCustomer, String NameOfItem, String DateOfItem, float PriceOfItem) {
        try {
            Customer matchedCustomer = search_Customer(IDOfCustomer);
            if (matchedCustomer != null) {
                LinkedList<Item> customerItems = items.get(customers.indexOf(matchedCustomer));
                Item newItem = new Item(NameOfItem, DateOfItem, PriceOfItem, null);
                customerItems.add(newItem);

            } else {
                throw new IDNotFoundException("ID not found: " + IDOfCustomer);
            }
        } catch (IDNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Float getTotalTradeofCustomer(int IDOfCustomer) {
        float totalTrade = 0;
        Customer foundCustomer = search_Customer(IDOfCustomer);

        if (foundCustomer != null) {
            LinkedList<Item> itemList = items.get(customers.indexOf(foundCustomer));
            for (Item item : itemList) {
                totalTrade += item.getPrice();
            }
            return totalTrade;
        } else {
            return 0.0f;
        }
    }

    @Override
    public Float getTotalTrade() {
        float totalTradeOfCustomer = 0;

        for (LinkedList<Item> itemList : items) {
            for (Item item : itemList) {
                totalTradeOfCustomer += item.getPrice();
            }
        }
        return totalTradeOfCustomer;
    }

    @Override
    public void listItems(int IDOfCustomer) {
        Customer customerItems = search_Customer(IDOfCustomer);

        if (customerItems != null) {
            LinkedList<Item> itemList = items.get(customers.indexOf(customerItems));
            Collections.sort(itemList, (item1, item2) -> Float.compare(item1.getPrice(), item2.getPrice()));
            System.out.println(customerItems + " Item List :");
            for (Item item : itemList) {
                if (item.getPrice() != 0.0f) {
                    System.out.println(item);
                }
            }
        } else {
            System.out.println("Customer ID " + IDOfCustomer + " not found.");
        }
    }

    @Override
    public Customer search_Customer(int IDOfCustomer) {
        for (Customer customer : customers) {
            if (customer.getID() == IDOfCustomer) {

                return customer;
            }
        }

        return null;
    }

    @Override
    public void readFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            Customer currentProcessedCustomer = null;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                if (data.length >= 3) {
                    if (data[0].matches("\\d+")) {
                        int IDOfCustomer = Integer.parseInt(data[0]);
                        currentProcessedCustomer = search_Customer(IDOfCustomer);

                        if (currentProcessedCustomer != null) {
                            String itemName = data[1];
                            String date = data[2];
                            float price = Float.parseFloat(data[3]);

                            addNewItem(IDOfCustomer, itemName, date, price);
                        } else {
                        }
                    } else {
                        String name = data[0];
                        String surname = data[1];
                        int ID = Integer.parseInt(data[2]);
                        currentProcessedCustomer = new Customer(name, surname, ID, null);
                        addCustomer(currentProcessedCustomer);
                    }
                } else {
                }
            }
            System.out.println("The content of file has been read");

        } catch (IOException e) {
            System.err.println("An error occurred during the file reading process.: " + e.getMessage());
        }
    }
}
