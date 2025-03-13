package com.megacitycab.dao;

import com.megacitycab.model.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Vehicle-related database operations.
 */
public class VehicleDAO {
    private Connection connection;

    /**
     * ✅ Constructor initializes DB connection
     * @param connection The database connection instance.
     */
    public VehicleDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * ✅ Retrieves all vehicles from the database.
     * @return List of all vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getString("plateNumber"),
                        rs.getString("type"),
                        rs.getString("model"),
                        rs.getDouble("ratePerKm"),
                        rs.getBoolean("availability"),
                        rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    /**
     * ✅ Adds a vehicle to the database.
     * @param vehicle The Vehicle object to be added.
     * @return true if insertion is successful, false otherwise.
     */
    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (plateNumber, type, model, ratePerKm, availability, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, vehicle.getPlateNumber());
            pstmt.setString(2, vehicle.getType());
            pstmt.setString(3, vehicle.getModel());
            pstmt.setDouble(4, vehicle.getRatePerKm());
            pstmt.setBoolean(5, vehicle.isAvailable());
            pstmt.setString(6, vehicle.getImagePath());
            return pstmt.executeUpdate() > 0; // ✅ Returns success/failure
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ✅ Deletes a vehicle from the database.
     * @param plateNumber The plate number of the vehicle to delete.
     * @return true if deletion is successful, false otherwise.
     */
    public boolean deleteVehicle(String plateNumber) {
        String query = "DELETE FROM vehicles WHERE plateNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, plateNumber);
            return pstmt.executeUpdate() > 0; // ✅ Returns success/failure
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}