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

    public User login(String username, String password) {
        try {
            return userDAO.authenticate(username, password);
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return null;
        }
    }

    public void registerCustomer(User user, Customer customer) {
        try {
            userDAO.addUser(user);
            customerDAO.addCustomer(customer);
        } catch (Exception e) {
            System.err.println("Registration failed: " + e.getMessage());
        }
    }
}