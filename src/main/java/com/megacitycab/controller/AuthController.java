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

    public User login(String username, String password, String role) {
        try {
            return userDAO.authenticate(username, password, role);
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return null;
        }
    }

    public User registerCustomer(String username, String password, String address, String nic, String phone) {
        try {
            Customer customer = new Customer("AUTO_GENERATED", username, password, address, nic, phone);
            customerDAO.addCustomer(customer);
            return customer;
        } catch (Exception e) {
            System.err.println("Registration failed: " + e.getMessage());
            return null;
        }
    }
}