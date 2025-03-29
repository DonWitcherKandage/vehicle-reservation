package com.megacitycab.dao;

import com.megacitycab.model.Customer;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Add a new customer
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO customers (userId, username, password, address, nic, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, customer.getUserId());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getNic());
            statement.setString(6, customer.getPhoneNumber());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println(" Error Adding Customer: " + e.getMessage());
            return false;
        }
    }

    // Update customer details
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customers SET username = ?, password = ?, address = ?, nic = ?, phoneNumber = ? WHERE userId = ?";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getNic());
            statement.setString(5, customer.getPhoneNumber());
            statement.setInt(6, customer.getUserId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error Updating Customer: " + e.getMessage());
            return false;
        }
    }


    // Delete a customer
    public boolean deleteCustomer(int userId) {
        String query = "DELETE FROM customers WHERE userId = ?";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println(" Error Deleting Customer: " + e.getMessage());
            return false;
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";

        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String nic = resultSet.getString("nic");
                String phoneNumber = resultSet.getString("phoneNumber");

                Customer customer = new Customer(userId, username, password, address, nic, phoneNumber);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println(" Error Fetching Customers: " + e.getMessage());
        }

        return customers;
    }
}