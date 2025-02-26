package com.megacitycab.dao;

import com.megacitycab.model.Car;
import com.megacitycab.util.DatabaseManager;
import java.sql.*;


public class CarDAO {
    private static final String INSERT_CAR = 
        "INSERT INTO cars (car_id, model, license_plate) VALUES (?, ?, ?)";
    private static final String UPDATE_AVAILABILITY = 
        "UPDATE cars SET availability = ? WHERE car_id = ?";

    public void addCar(Car car) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CAR)) {
            stmt.setString(1, car.getCarId());
            stmt.setString(2, car.getModel());
            stmt.setString(3, car.getLicensePlate());
            stmt.executeUpdate();
        }
    }

    public void updateAvailability(String carId, boolean available) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_AVAILABILITY)) {
            stmt.setBoolean(1, available);
            stmt.setString(2, carId);
            stmt.executeUpdate();
        }
    }
}