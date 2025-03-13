package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing driver records in the database.
 */
public class DriverDAO {
    private final DatabaseManager dbManager;

    // ✅ Constructor initializes DatabaseManager
    public DriverDAO() {
        this.dbManager = DatabaseManager.getInstance();
    }

    /**
     * ✅ Adds a new driver to the database.
     * @param driver The Driver object.
     * @return true if insertion is successful, false otherwise.
     */
    public boolean addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (driverID, name, availability) VALUES (?, ?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getDriverId());
            stmt.setString(2, driver.getName());
            stmt.setBoolean(3, driver.isAvailable());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ✅ Updates an existing driver's availability status.
     * @param driverId The driver's ID.
     * @param availability The new availability status.
     * @return true if update is successful, false otherwise.
     */
    public boolean updateDriverAvailability(String driverId, boolean availability) {
        String sql = "UPDATE drivers SET availability = ? WHERE driverID = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, availability);
            stmt.setString(2, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ✅ Fetches all drivers from the database.
     * @return List of Driver objects.
     */
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                drivers.add(new Driver(
                    rs.getString("driverID"),
                    rs.getString("name"),
                    rs.getBoolean("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    /**
     * ✅ Assigns a driver to a vehicle.
     * @param driverId The ID of the driver.
     * @param vehicleId The ID of the vehicle.
     * @return true if assignment is successful, false otherwise.
     */
    public boolean assignDriver(String driverId, String vehicleId) {
        String sql = "UPDATE vehicles SET assignedDriverId = ? WHERE plateNumber = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driverId);
            stmt.setString(2, vehicleId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ✅ Removes a driver from the database.
     * @param driverId The ID of the driver.
     * @return true if deletion is successful, false otherwise.
     */
    public boolean deleteDriver(String driverId) {
        String sql = "DELETE FROM drivers WHERE driverID = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}