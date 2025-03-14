package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import com.megacitycab.util.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    // Add a new driver
    public boolean addDriver(Driver driver) {
        String query = "INSERT INTO drivers (driverID, name, availability) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, driver.getDriverId());
            statement.setString(2, driver.getName());
            statement.setBoolean(3, driver.isAvailable());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error Adding Driver: " + e.getMessage());
            return false;
        }
    }

    // Update driver details
    public boolean updateDriver(Driver driver) {
        String query = "UPDATE drivers SET name = ?, availability = ? WHERE driverID = ?";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, driver.getName());
            statement.setBoolean(2, driver.isAvailable());
            statement.setString(3, driver.getDriverId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error Updating Driver: " + e.getMessage());
            return false;
        }
    }

    // Delete a driver
    public boolean deleteDriver(String driverID) {
        String query = "DELETE FROM drivers WHERE driverID = ?";
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, driverID);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error Deleting Driver: " + e.getMessage());
            return false;
        }
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers";

        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("driverID");
                String name = resultSet.getString("name");
                boolean availability = resultSet.getBoolean("availability");

                Driver driver = new Driver(id, name, availability);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error Fetching Drivers: " + e.getMessage());
        }

        return drivers;
    }
}