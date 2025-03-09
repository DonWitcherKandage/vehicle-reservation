package com.megacitycab.controller;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.Customer;
import com.megacitycab.model.User;

public class AuthController {
    private final UserDAO userDAO;
    private final CustomerDAO customerDAO;

    public AuthController(UserDAO userDAO, CustomerDAO customerDAO) {
        this.userDAO = userDAO;
        this.customerDAO = customerDAO;
    }

    /**  Handles user login, returns a `User` object or `null` */
    public User login(String username, String password) {
        try {
            return userDAO.authenticate(username, password);
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return null;
        }
    }

    /**  Handles customer registration and returns a `User` */
    public User registerCustomer(String userID, String username, String password, String address, String nic, String phone) {
        try {
            // Create user and customer objects
            Customer customer = new Customer(userID, username, password, address, nic, phone);
            
            // Save to database
            userDAO.addUser(customer);
            customerDAO.addCustomer(customer);

            return customer; //  Return the registered user
        } catch (Exception e) {
            System.err.println("Registration failed: " + e.getMessage());
            return null; //  Return null if registration fails
        }
    }
}