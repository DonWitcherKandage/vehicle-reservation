package com.megacitycab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/megacitycab";  // Replace "megacitycab" with your DB name
        String USER = "pramudithakandage";  // Your MySQL username
        String password = "SandH199709";  // Your MySQL password

        try {
            // Load MySQL JDBC Driver (optional)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(URL, USER, password);

            if (conn != null) {
                System.out.println("✅ Database connection successful!");
                conn.close(); // Close connection
            } else {
                System.out.println("❌ Database connection failed!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found! Ensure JAR is added.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Error connecting to database!");
            e.printStackTrace();
        }
    }
}
