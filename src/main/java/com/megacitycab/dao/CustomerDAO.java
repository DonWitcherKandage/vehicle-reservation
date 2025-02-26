package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;


public class CustomerDAO {
    private static final String INSERT_CUSTOMER = 
        "INSERT INTO customers VALUES (?, ?, ?, ?, ?)";

    public void addCustomer(Customer customer) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getNic());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.executeUpdate();
        }
    }
}
