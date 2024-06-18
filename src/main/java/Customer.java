/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Erkan_Kaya_HW3;

/**
 *
 * @author eerka
 */
public class Customer {
    private String Name;
    private String Surname;
    private int ID;
    private Item Link;
    
    public Customer() {
        this.Name = "";
        this.Surname = "";
        this.ID = 0;
        this.Link = null;
    }
     public Customer(String name, String surname, int id, Item link) {
        this.Name = name;
        this.Surname = surname;
        this.ID = id;
        this.Link = link;
    }
     public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }
    public Item getLink() {
        return Link;
    }

    public void setLink(Item link) {
        Link = link;
    }
    @Override
public String toString() {
    return  getName() +" "+ getSurname() +" "+ getID();
}
}
