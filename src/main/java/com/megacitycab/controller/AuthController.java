package com.megacitycab.controller;

import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.Customer;
import com.megacitycab.model.User;

/**
 * AuthController handles authentication and registration.
 */
public class AuthController {
    private UserDAO userDAO;

    // ✅ Correctly initialize UserDAO
    public AuthController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Logs in a user with username and password.
     */
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    /**
     * Registers a new customer.
     */
    public void registerCustomer(String username, String password, String address, String nic, String phoneNumber) {
        // ✅ Matching constructor with ⁠ int userId ⁠
        Customer customer = new Customer(0, username, password, address, nic, phoneNumber);
        userDAO.registerCustomer(customer);
    }
}