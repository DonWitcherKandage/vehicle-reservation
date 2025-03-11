package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import com.megacitycab.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {

    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (bookingId, customerId, carId, driverId, destination, bookingDate, totalAmount, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingId());
            stmt.setString(2, booking.getCustomerId());
            stmt.setString(3, booking.getCarId());
            stmt.setString(4, booking.getDriverId());
            stmt.setString(5, booking.getDestination());
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(booking.getBookingDate()));
            stmt.setDouble(7, booking.getTotalAmount());
            stmt.setString(8, booking.getStatus());

            stmt.executeUpdate();
            System.out.println("Booking added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}