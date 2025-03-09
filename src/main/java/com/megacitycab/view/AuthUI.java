package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.model.User;
import java.util.Scanner;

/** Handles user authentication UI */
public class AuthUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthController authController; // Store reference to AuthController

    /**  Constructor that accepts AuthController */
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

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    handleRegistration();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    /** Handles user registration */
    private void handleRegistration() {
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter NIC: ");
        String nic = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        // Fix: Call `registerCustomer()` with correct parameters
        User user = authController.registerCustomer(userID, username, password, address, nic, phone);
        if (user != null) {
            System.out.println("Registration successful! Welcome, " + user.getUserName());
        } else {
            System.out.println("Error: Registration failed!");
        }
    }

    /** Handles user login */
    private void handleLogin() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Fix: `login()` returns `User`, not boolean
        User user = authController.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUserName());
        } else {
            System.out.println("Invalid credentials!");
        }
    }
}
