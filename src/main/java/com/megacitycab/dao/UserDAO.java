package com.megacitycab.dao;

import com.megacitycab.model.User;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;

public class UserDAO {
    private static final String FIND_USER = "SELECT * FROM users WHERE username = ?";
    private static final String INSERT_USER = 
        "INSERT INTO users (userID, username, password, role) VALUES (?, ?, ?, ?)";

    /** Generates a unique User ID */
    private String generateUserID() throws SQLException {
        String newUserID = "USR" + System.currentTimeMillis(); // Simple unique ID generation
        return newUserID;
    }

    public void addUser(User user) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {

            String userID = generateUserID(); //  Auto-generate User ID
            stmt.setString(1, userID);
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, (user instanceof com.megacitycab.model.Customer) ? "CUSTOMER" : "MANAGER");
            stmt.executeUpdate();
        }
    }

    public User authenticate(String username, String password, String role) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND role = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, role);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getString("password").equals(password)) {
                return new User(rs.getString("userID"), username, password) {};
            }
        }
        return null;
    }
}