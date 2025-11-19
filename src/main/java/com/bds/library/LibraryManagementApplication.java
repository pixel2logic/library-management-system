package com.bds.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class
 * Entry point for the Library Management System
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
        System.out.println("\n======================================");
        System.out.println("Library Management System Started!");
        System.out.println("Access the application at: http://localhost:8080");
        System.out.println("H2 Console: http://localhost:8080/h2-console");
        System.out.println("======================================\n");
    }
}
