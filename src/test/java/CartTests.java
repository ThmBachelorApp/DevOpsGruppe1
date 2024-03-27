package com.smartshop.service;

import com.smartshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testAddProductAndTotalPrice() {
        Product product1 = new Product("1", "Java Book", 29.99);
        Product product2 = new Product("2", "Spring Book", 39.99);

        cart.addProduct(product1);
        cart.addProduct(product2);

        assertEquals(2, cart.getProductCount(), "Der Warenkorb sollte 2 Produkte enthalten.");
        assertEquals(69.98, cart.getTotalPrice(), "Die Gesamtsumme sollte 69.98 betragen.");
    }
}
