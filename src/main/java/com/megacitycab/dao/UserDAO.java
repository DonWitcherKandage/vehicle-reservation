package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.model.Manager;
import com.megacitycab.model.User;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Logs in a customer and returns Customer object
    public User loginCustomer(String username, String password) {
        String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String address = resultSet.getString("address");
                String nic = resultSet.getString("nic");
                String phoneNumber = resultSet.getString("phoneNumber");

                return new Customer(userId, username, password, address, nic, phoneNumber);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error Fetching Customer: " + e.getMessage());
        }
        return null;
    }

    // Logs in a manager and returns Manager object
    public User loginManager(String username, String password) {
        String query = "SELECT * FROM managers WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String address = resultSet.getString("address");
                String nic = resultSet.getString("nic");
                String phoneNumber = resultSet.getString("phoneNumber");

                return new Manager(userId, username, password, address, nic, phoneNumber);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error Fetching Manager: " + e.getMessage());
        }
        return null;
    }

    // Registers a new customer
    public boolean registerCustomer(String username, String password, String address, String nic, String phoneNumber) {
        String query = "INSERT INTO customers (username, password, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, address);
            statement.setString(4, nic);
            statement.setString(5, phoneNumber);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error Registering Customer: " + e.getMessage());
            return false;
        }
    }

    // Registers a new manager
    public boolean registerManager(String username, String password, String address, String nic, String phoneNumber) {
        String query = "INSERT INTO managers (username, password, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, address);
            statement.setString(4, nic);
            statement.setString(5, phoneNumber);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error Registering Manager: " + e.getMessage());
            return false;
        }
    }
}