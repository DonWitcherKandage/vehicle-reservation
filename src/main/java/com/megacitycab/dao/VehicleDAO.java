package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import com.megacitycab.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (plateNumber, type, model, ratePerKm, availability, imagePath) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getModel());
            stmt.setDouble(4, vehicle.getRatePerKm());
            stmt.setBoolean(5, vehicle.isAvailable());
            stmt.setString(6, vehicle.getImagePath());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(String plateNumber) {
        String sql = "DELETE FROM vehicles WHERE plateNumber = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plateNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vehicles.add(new Vehicle(rs.getString("plateNumber"), rs.getString("type"),
                        rs.getString("model"), rs.getDouble("ratePerKm"), rs.getBoolean("availability"),
                        rs.getString("imagePath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}