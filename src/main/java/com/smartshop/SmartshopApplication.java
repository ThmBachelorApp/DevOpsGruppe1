package com.smartshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartshopApplication {

    private static final Logger logger = LoggerFactory.getLogger(SmartshopApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmartshopApplication.class, args);
    }
    
    // Beispiel einer Methode, die Logger verwendet
    public void logMessage(String message) {
        logger.info("Logging message: {}", message);
    }

    

}
