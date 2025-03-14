package com.megacitycab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static volatile DatabaseManager instance; // Use volatile for thread safety
    private static final String URL = "jdbc:mysql://localhost:3306/megacitycab";
    private static final String USER = "pramudithakandage"; // Use the new user
    private static final String PASSWORD = "SandH199709"; // Replace with the actual password

    // Private constructor to prevent instantiation
    private DatabaseManager() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver Not Found!");
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver Not Found!", e);
        }
    }

    // Singleton Pattern with double-checked locking
    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    // Get a new database connection
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database Connection Established Successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Failed to Establish Database Connection!");
            e.printStackTrace();
            throw new RuntimeException("Failed to Establish Database Connection!", e);
        }
        return connection;
    }

    // Close the connection
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Database Connection Closed Successfully!");
            } catch (SQLException e) {
                System.err.println("❌ Error Closing Database Connection!");
                e.printStackTrace();
            }
        }
    }
}