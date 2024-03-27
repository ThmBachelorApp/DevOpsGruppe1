package com.smartshop.service;

import com.smartshop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    // Methode, um die Anzahl der Produkte im Warenkorb zu bekommen
    public int getProductCount() {
        return products.size();
    }
}
