package com.megacitycab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * Manages database connections
 */
public class DatabaseManager {
    private static Connection connection;
    private static final String PROP_FILE = "database.properties";

    private DatabaseManager() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream(PROP_FILE)) {
                if (input == null) {
                    throw new IOException("Database properties file not found!");
                }

                Properties props = new Properties();
                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                if (url == null || url.isEmpty()) {
                    throw new SQLException("Database URL is missing in properties file.");
                }

                // Load MySQL JDBC Driver explicitly
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Database Connected Successfully!");
            } catch (IOException e) {
                throw new SQLException("Failed to load database properties file", e);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Database Connection Closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}