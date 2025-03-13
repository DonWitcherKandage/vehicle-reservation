package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.model.User;
import com.megacitycab.util.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO handles database interactions for user authentication and registration.
 */
public class UserDAO {
    private Connection connection;

    // ✅ Default constructor to initialize DB connection
    public UserDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    /**
     * Registers a new customer in the database.
     */
    public void registerCustomer(Customer customer) {
        String query = "INSERT INTO users (username, password, role, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, "CUSTOMER");  // Role is always CUSTOMER
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getNic());
            stmt.setString(6, customer.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authenticates a user based on username and password.
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
}