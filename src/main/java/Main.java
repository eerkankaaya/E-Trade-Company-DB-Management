/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Erkan_Kaya_HW3;

/**
 *
 * @author eerka
 */
public class Main {
    public static void main(String[] args) {
        EEM480DataBase MyDataBase = new EEM480DataBase();
        Customer DummyCustomer = new Customer();
        MyDataBase.readFromFile("C:\\Users\\eerka\\Desktop\\MyData.txt");
        Float exps= MyDataBase.getTotalTradeofCustomer(13456);
        System.out.println(MyDataBase.search_Customer(13456)+" Total Expense : "+exps);
        System.out.println(" The Total Trade : "+ MyDataBase.getTotalTrade());
        MyDataBase.listItems(13456);
        Customer newc = new Customer();
        newc= MyDataBase.getNewCustomer("Ali","Veli", 4950);
        MyDataBase.addCustomer(newc);
        MyDataBase.addNewItem(4950, "Karburator", "Monday", 145.8f);
        MyDataBase.addNewItem(4950, "Laptop", "Tuesday", 2340);
        System.out.println("The Total Trade : " + MyDataBase.getTotalTrade());
        MyDataBase.listItems(4950);
        
    }
}
