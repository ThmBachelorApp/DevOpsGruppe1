package com.example.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    // Erstellen eines Logger-Objekts für diese Klasse
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        // Verwendung des Loggers statt System.out.println
        logger.info("Hello, World!");
    }
}
