/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Erkan_Kaya_HW3;

/**
 *
 * @author eerka
 */
public class Item {
 private String ItemName;
 private String Date;
 private float Price;
 private Item Link;
 
 public Item(String itemName, String date, float price, Item link) {
        this.ItemName = itemName;
        this.Date = date;
        this.Price = price;
        this.Link = link;
    }
  public String getItemName() {
        return ItemName;
    }

    public String getDate() {
        return Date;
    }
    public Item getLink() {
        return Link;
    }
     public float getPrice() {
        return Price;
    }
     

    public void setLink(Item link) {
        Link = link;
    }
    @Override
    public String toString() {
    return getItemName() + " " + getDate() + " " + getPrice();
}
}
