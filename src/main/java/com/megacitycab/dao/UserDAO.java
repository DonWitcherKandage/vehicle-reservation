package com.megacitycab.dao;

import com.megacitycab.model.User;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;


public class UserDAO {
    private static final String INSERT_USER = 
        "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
    private static final String FIND_USER = 
        "SELECT * FROM users WHERE username = ?";

    public void addUser(User user) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
        }
    }

    public User authenticate(String username, String password) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_USER)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getString("password").equals(password)) {
                return new User(username, password, rs.getString("role"));
            }
            return null;
        }
    }
}