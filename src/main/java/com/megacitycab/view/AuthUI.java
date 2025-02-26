package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.model.Customer;
import com.megacitycab.model.User;
import java.util.Scanner;

public class AuthUI implements AutoCloseable {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthController authController;

    public AuthUI(AuthController authController) {
        this.authController = authController;
    }

    public void showAuthMenu() {
        while (true) {
            System.out.println("\n=== Mega City Cab ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            switch (scanner.nextLine()) {
                case "1" -> handleLogin();
                case "2" -> handleRegistration();
                case "3" -> {
                    close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void handleLogin() {
        String username = getInput("Username: ");
        String password = getInput("Password: ");
        
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty!");
            return;
        }

        User user = authController.login(username, password);
        
        if (user != null) {
            System.out.println("Welcome " + user.getUsername() + "!");
            // Redirect to appropriate dashboard
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void handleRegistration() {
        String username = getInput("Username: ");
        String password = getInput("Password: ");
        
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty!");
            return;
        }

        User user = new User(username, password, "CUSTOMER");
        Customer customer = new Customer(
            username,
            getInput("Full Name: "),
            getInput("NIC: "),
            getInput("Phone: "),
            getInput("Address: ")
        );

        try {
            authController.registerCustomer(user, customer);
            System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}