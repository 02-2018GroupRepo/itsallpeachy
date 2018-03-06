package com.company;

public class Product {
    private String name;
    private double price;
    private String description;
    private int qty;



    public Product(String name, String description, double price, int qty) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.qty = qty;
    }

    public Product(String name, String description, double price) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.qty = 0;
    }

    public void decrementStock(){
        if(this.qty > 0) {
            this.qty--;
            System.out.println("Stock is " + this.qty);
        }
        else{
            System.out.println("Out of Stock");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}