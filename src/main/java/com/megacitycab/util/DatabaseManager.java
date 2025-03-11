package com.megacitycab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // Singleton instance
    private static DatabaseManager instance;
    private Connection connection;

    // Private constructor prevents instantiation
    private DatabaseManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/megacitycab";
            String user = "root"; // Replace with your MySQL username
            String password = "password"; // Replace with your password
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed!", e);
        }
    }

    // Provides a global access point to the instance
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    // Returns the database connection
    public Connection getConnection() {
        return connection;
    }
}