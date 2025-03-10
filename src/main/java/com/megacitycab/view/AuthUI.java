package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.model.User;
import java.util.Scanner;

public class AuthUI {
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

    private void handleLogin() {
        System.out.println("Select Role:");
        System.out.println("1. Customer");
        System.out.println("2. Manager");
        System.out.print("Enter choice: ");
        String roleChoice = scanner.nextLine();

        String role = roleChoice.equals("1") ? "CUSTOMER" : "MANAGER";

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        User user = authController.login(username, password, role);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUserName());
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void handleRegistration() {
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

        // Validate inputs using regex
        if (!isValidUsername(username) || !isValidPassword(password) || 
            !isValidNIC(nic) || !isValidPhoneNumber(phone)) {
            System.out.println("Error: Invalid input format!");
            return;
        }

        User user = authController.registerCustomer(username, password, address, nic, phone);
        if (user != null) {
            System.out.println("Registration successful! Welcome, " + user.getUserName());
        } else {
            System.out.println("Error: Registration failed!");
        }
    }

    /** Username Validation */
    private boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9._-]{5,20}$");
    }

    /** Password Validation: 8+ chars, 1 uppercase, 1 number, 1 special char */
    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }

    /** NIC Validation: 9 or 12 digits */
    private boolean isValidNIC(String nic) {
        return nic.matches("^\\d{9}[VvXx]?$") || nic.matches("^\\d{12}$");
    }

    /** Phone Number Validation: Sri Lankan 10-digit format */
    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^07\\d{8}$");
    }
}