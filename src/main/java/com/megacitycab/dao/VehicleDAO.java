package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import com.megacitycab.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        DatabaseManager dbManager = DatabaseManager.getInstance(); // Use singleton

        try (Connection connection = dbManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicles")) {

            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle(
                        String.valueOf(resultSet.getInt("id")), // Convert int to String
                        resultSet.getString("model"),
                        resultSet.getString("license_plate"), // Match with plateNumber
                        resultSet.getDouble("rate_per_km"), // New field
                        resultSet.getBoolean("available"),
                        resultSet.getString("image_path") // New field
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void updateVehicleAvailability(String vehicleId, boolean available) {
        DatabaseManager dbManager = DatabaseManager.getInstance(); // Use singleton

        try (Connection connection = dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE vehicles SET available = ? WHERE id = ?")) {

            statement.setBoolean(1, available);
            statement.setString(2, vehicleId); // Use String for ID
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}