package org.example;

public class Shelf {
    private String name;
    private String code;
    private double price;
    private int quantity;

    public Shelf(String code, String name, double price, int quantity){
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public void updateQuantity(int quantity){
        this.quantity += quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
