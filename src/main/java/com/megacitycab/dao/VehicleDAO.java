package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.UUID;

/**
 * VehicleDAO - Handles Database Operations for Vehicles.
 */
public class VehicleDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehicle_reservation";
    private static final String DB_USER = "your_db_user";
    private static final String DB_PASSWORD = "your_db_password";

    public VehicleDAO() {
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS vehicles ("
                + "id VARCHAR(50) PRIMARY KEY, "
                + "model VARCHAR(100) NOT NULL, "
                + "plate_number VARCHAR(20) NOT NULL, "
                + "type VARCHAR(50) NOT NULL, "
                + "rate_per_km DOUBLE NOT NULL, "
                + "is_available TINYINT(1) NOT NULL, "
                + "image_path VARCHAR(255) NULL"
                + ")";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle(String model, String plateNumber, String type, double ratePerKm, boolean isAvailable, String imagePath) {
        String sql = "INSERT INTO vehicles (id, model, plate_number, type, rate_per_km, is_available, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, UUID.randomUUID().toString()); // Generate Unique ID
            pstmt.setString(2, model);
            pstmt.setString(3, plateNumber);
            pstmt.setString(4, type);
            pstmt.setDouble(5, ratePerKm);
            pstmt.setInt(6, isAvailable ? 1 : 0); // Convert boolean to TINYINT(1)
            pstmt.setString(7, imagePath);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Vehicle> getAllVehicles() {
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        String sql = "SELECT * FROM vehicles";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getString("id"),
                        rs.getString("model"),
                        rs.getString("plate_number"),
                        rs.getString("type"),
                        rs.getDouble("rate_per_km"),
                        rs.getBoolean("is_available"),
                        rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void deleteVehicle(String vehicleId) {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(String vehicleId, boolean isAvailable) {
        String sql = "UPDATE vehicles SET is_available = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isAvailable ? 1 : 0);
            pstmt.setString(2, vehicleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}