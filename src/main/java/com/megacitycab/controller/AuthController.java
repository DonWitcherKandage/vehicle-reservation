package com.megacitycab.controller;

import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Manager;
import com.megacitycab.model.User;

/**
 * Handles authentication and user registration.
 */
public class AuthController {
    private final UserDAO userDAO;

    public AuthController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Logs in a user and returns User object.
     */
    public User login(String username, String password, String role) {
        User user;
        if (role.equalsIgnoreCase("CUSTOMER")) {
            user = userDAO.loginCustomer(username, password);
        } else if (role.equalsIgnoreCase("MANAGER")) {
            user = userDAO.loginManager(username, password);
        } else {
            user = null;
        }
        return user;
    }

    /**
     * Registers a new customer.
     */
    public boolean registerCustomer(String username, String password, String address, String nic, String phoneNumber) {
        return userDAO.registerCustomer(username, password, address, nic, phoneNumber);
    }

    /**
     * Registers a new manager.
     */
    public boolean registerManager(String username, String password, String address, String nic, String phoneNumber) {
        return userDAO.registerManager(username, password, address, nic, phoneNumber);
    }
}