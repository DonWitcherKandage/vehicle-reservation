package com.megacitycab.controller;

import com.megacitycab.dao.UserDAO;
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
     * ✅ Logs in a user and returns User object.
     */
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    /**
     * ✅ Registers a new customer.
     */
    public boolean registerCustomer(String username, String password, String address, String nic, String phoneNumber) {
        return userDAO.registerCustomer(username, password, address, nic, phoneNumber);
    }
}