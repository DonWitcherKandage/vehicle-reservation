package com.megacitycab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    // ✅ Update your database credentials here
    private static final String URL = "jdbc:mysql://localhost:3306/megacitycab";
    private static final String USER = "pramudithakandage";  // Use the new user
    private static final String PASSWORD = "SandH199709"; // Replace with the actual password

    private DatabaseManager() {
        try {
            // ✅ Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Establish Connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database Connection Successful!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database Connection Failed!");
            e.printStackTrace();
            throw new RuntimeException("Database connection failed!", e);
        }
    }

    // ✅ Singleton Pattern - Ensures only one instance of DatabaseManager
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    // ✅ Get Database Connection
    public Connection getConnection() {
        return connection;
    }

    // ✅ Close Connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Database Connection Closed.");
            } catch (SQLException e) {
                System.err.println("❌ Error Closing Database Connection!");
                e.printStackTrace();
            }
        }
    }
}