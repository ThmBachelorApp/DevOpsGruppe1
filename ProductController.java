package com.example.onlineshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public String listProducts() {
        // Dummy-Implementierung, ersetze dies durch deine Logik
        return "List of Products";
    }
}
