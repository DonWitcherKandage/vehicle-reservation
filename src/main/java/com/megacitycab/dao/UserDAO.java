package com.megacitycab.dao;

import com.megacitycab.model.User;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Manager;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;

public class UserDAO {
    private static final String INSERT_USER = 
        "INSERT INTO users (userID, username, password, role) VALUES (?, ?, ?, ?)";
    private static final String FIND_USER = 
        "SELECT * FROM users WHERE username = ?";

    /** Adds a new user to the database */
    public void addUser(User user) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {
            
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getUserName()); // Fixed getUsername()
            stmt.setString(3, user.getPassword());
            stmt.setString(4, (user instanceof Customer) ? "CUSTOMER" : "MANAGER");
            stmt.executeUpdate();
        }
    }

    /** Authenticates a user and returns a `Customer` or `Manager` object */
    public User authenticate(String username, String password) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_USER)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String role = rs.getString("role");
                String userID = rs.getString("userID");

                // Verify password securely
                if (password.equals(storedPassword)) { // Simple check, hashing recommended
                    if ("CUSTOMER".equals(role)) {
                        return new Customer(userID, username, storedPassword, "Default Address", "Default NIC", "Default Phone"); // Fixed constructor
                    } else if ("MANAGER".equals(role)) {
                        return new Manager(userID, username, storedPassword, userID);
                    }
                }
            }
            return null; // Invalid login
        }
    }
}
