package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Registers a new customer in the database
    public int registerCustomer(Customer customer) {
        String sql = "INSERT INTO users (username, password, role, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, "CUSTOMER");  // Customers only
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getNic());
            stmt.setString(6, customer.getPhoneNumber());

            stmt.executeUpdate();

            // Retrieve the generated auto-incremented userId
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  // Return the generated userId
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if registration failed
    }
}