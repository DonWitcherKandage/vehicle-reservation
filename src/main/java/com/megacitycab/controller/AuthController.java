package com.megacitycab.controller;

import com.megacitycab.dao.UserDAO;
import com.megacitycab.model.Customer;
import com.megacitycab.model.User;

public class AuthController {
    private UserDAO userDAO;

    public AuthController() {
        this.userDAO = new UserDAO();
    }

    public void registerCustomer(String username, String password, String address, String nic, String phoneNumber) {
        Customer customer = new Customer(0, username, password, address, nic, phoneNumber);
        userDAO.registerCustomer(customer);
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
}