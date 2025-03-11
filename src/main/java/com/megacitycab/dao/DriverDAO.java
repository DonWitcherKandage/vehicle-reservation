package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import com.megacitycab.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    public void addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (driverId, name, availability) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getDriverId());
            stmt.setString(2, driver.getName());
            stmt.setBoolean(3, driver.isAvailable());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDriver(String driverId) {
        String sql = "DELETE FROM drivers WHERE driverId = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driverId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                drivers.add(new Driver(rs.getString("driverId"), rs.getString("name"), rs.getBoolean("availability")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
}