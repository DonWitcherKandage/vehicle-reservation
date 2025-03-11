package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.model.User;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Registers a new customer in the database
    public void registerCustomer(Customer customer) {
        String sql = "INSERT INTO users (userId, username, password, role, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getUserId());
            stmt.setString(2, customer.getUsername());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getRole());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getNic());
            stmt.setString(7, customer.getPhoneNumber());

            stmt.executeUpdate();
            System.out.println("✅ Customer Registered Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Authenticates User (Customer or Manager)
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userId = rs.getString("userId");
                String role = rs.getString("role");

                if (role.equals("CUSTOMER")) {
                    return new Customer(userId, username, password, rs.getString("address"), rs.getString("nic"), rs.getString("phoneNumber"));
                } else {
                    return new User(userId, username, password, "MANAGER") {};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}