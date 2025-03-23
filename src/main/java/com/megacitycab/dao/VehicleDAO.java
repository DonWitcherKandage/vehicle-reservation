package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import com.megacitycab.util.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.UUID;

public class VehicleDAO {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    public void addVehicle(String model, String plateNumber, String type, 
                          double ratePerKm, boolean isAvailable, String imagePath) {
        String sql = "INSERT INTO vehicles (id, model, plate_number, type, rate_per_km, is_available, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, model);
            pstmt.setString(3, plateNumber);
            pstmt.setString(4, type);
            pstmt.setDouble(5, ratePerKm);
            pstmt.setBoolean(6, isAvailable);
            pstmt.setString(7, imagePath);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
        }
    }

    public ObservableList<Vehicle> getAllVehicles() {
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        String sql = "SELECT * FROM vehicles";
        
        try (Connection conn = dbManager.getConnection();
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
            System.err.println("Error fetching vehicles: " + e.getMessage());
        }
        return vehicles;
    }

    public void deleteVehicle(String vehicleId) {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, vehicleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
        }
    }

    public void updateVehicleAvailability(String vehicleId, boolean isAvailable) {
        String sql = "UPDATE vehicles SET is_available = ? WHERE id = ?";
        
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, isAvailable);
            pstmt.setString(2, vehicleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating vehicle availability: " + e.getMessage());
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET model = ?, plate_number = ?, type = ?, rate_per_km = ?, is_available = ?, image_path = ? WHERE id = ?";
        
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, vehicle.getModel());
            pstmt.setString(2, vehicle.getPlateNumber());
            pstmt.setString(3, vehicle.getType());
            pstmt.setDouble(4, vehicle.getRatePerKm());
            pstmt.setBoolean(5, vehicle.isAvailable());
            pstmt.setString(6, vehicle.getImagePath());
            pstmt.setString(7, vehicle.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
        }
    }
}