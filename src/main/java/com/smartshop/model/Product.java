package com.smartshop.model;

public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Wendet einen gegebenen Rabatt auf den Preis des Produkts an.
     * @param discount Der Rabatt als Prozentsatz (z.B. 0.20 für 20% Rabatt).
     */
    public void applyDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Der Rabatt muss ein Wert zwischen 0 und 1 sein.");
        }
        this.price -= this.price * discount;
    }
}
