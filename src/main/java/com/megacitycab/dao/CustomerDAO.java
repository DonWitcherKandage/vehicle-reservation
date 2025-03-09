package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static final String INSERT_CUSTOMER = 
        "INSERT INTO customers (userID, username, password, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_CUSTOMER = 
        "SELECT * FROM customers WHERE username = ?";
    private static final String GET_ALL_CUSTOMERS = 
        "SELECT * FROM customers";

    /** Adds a new customer to the database */
    public void addCustomer(Customer customer) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {
            
            stmt.setString(1, customer.getUserID());
            stmt.setString(2, customer.getUserName()); // Fixed getUsername()
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getCustomerAddress()); // Fixed: Address
            stmt.setString(5, customer.getNic());
            stmt.setString(6, customer.getPhoneNumber()); // Fixed getPhone()

            stmt.executeUpdate();
        }
    }

    /** Finds a customer by username */
    public Customer findCustomer(String username) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_CUSTOMER)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getString("userID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("nic"),
                    rs.getString("phoneNumber") // Fixed constructor parameters
                );
            }
        }
        return null;
    }

    /** Retrieves all customers */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_CUSTOMERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                    rs.getString("userID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("nic"),
                    rs.getString("phoneNumber") // Fixed constructor parameters
                ));
            }
        }
        return customers;
    }
}