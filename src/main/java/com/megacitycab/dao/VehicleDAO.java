package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import com.megacitycab.util.DatabaseManager; // Add this import
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    // Method to get all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String model = resultSet.getString("model");
                String plateNumber = resultSet.getString("plate_number");
                double ratePerKm = resultSet.getDouble("rate_per_km");
                boolean isAvailable = resultSet.getBoolean("is_available");
                String imagePath = resultSet.getString("image_path");

                // Create a new Vehicle object
                Vehicle vehicle = new Vehicle(id, model, plateNumber, ratePerKm, isAvailable, imagePath);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    // Method to get a vehicle by ID
    public Vehicle getVehicleById(String vehicleId) {
        String query = "SELECT * FROM vehicles WHERE id = ?";
        Vehicle vehicle = null;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String model = resultSet.getString("model");
                String plateNumber = resultSet.getString("plate_number");
                double ratePerKm = resultSet.getDouble("rate_per_km");
                boolean isAvailable = resultSet.getBoolean("is_available");
                String imagePath = resultSet.getString("image_path");

                // Create a new Vehicle object
                vehicle = new Vehicle(id, model, plateNumber, ratePerKm, isAvailable, imagePath);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    // Other methods (add, update, delete) can be added here
}