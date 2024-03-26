package com.smartshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartshopApplication.class, args);
    }
    
    // Hinzugefügte Methode, die wahrscheinlich von SonarQube bemängelt wird
    private static void unusedMethod() {
        System.out.println("Diese Methode wird nirgends aufgerufen.");
    }
}
