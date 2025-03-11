package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleDAO {

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (plateNumber, type, model, ratePerKm, availability) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getPlateNumber());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getModel());
            stmt.setDouble(4, vehicle.getRatePerKm());
            stmt.setBoolean(5, vehicle.isAvailable());

            stmt.executeUpdate();
            System.out.println("✅ Vehicle added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}