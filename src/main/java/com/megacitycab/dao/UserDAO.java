package com.megacitycab.dao;

import com.megacitycab.model.User;
import com.megacitycab.util.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles database interactions for user authentication and registration.
 */
public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    /**
     * ✅ Authenticates a user based on username and password.
     */
    public User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }

    /**
     * ✅ Registers a new customer.
     */
    public boolean registerCustomer(String username, String password, String address, String nic, String phoneNumber) {
        String query = "INSERT INTO users (username, password, role, address, nic, phoneNumber) VALUES (?, ?, 'CUSTOMER', ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, address);
            stmt.setString(4, nic);
            stmt.setString(5, phoneNumber);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}