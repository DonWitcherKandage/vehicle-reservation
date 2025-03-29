package com.megacitycab.controller;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Customer;

import java.util.List;

public class CustomerController {
    private final CustomerDAO customerDAO = new CustomerDAO();

    // Add a new customer
    public boolean addCustomer(int userId, String username, String password, String address, String nic, String phoneNumber) {
        Customer customer = new Customer(userId, username, password, address, nic, phoneNumber);
        return customerDAO.addCustomer(customer);
    }

    // Update customer details
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(int userId) {
        return customerDAO.deleteCustomer(userId);
    }
    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}