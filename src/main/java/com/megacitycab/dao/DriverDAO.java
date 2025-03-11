package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverDAO {

    public void addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (driverId, name, availability) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getDriverId());
            stmt.setString(2, driver.getName());
            stmt.setBoolean(3, driver.isAvailable());

            stmt.executeUpdate();
            System.out.println("✅ Driver added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}