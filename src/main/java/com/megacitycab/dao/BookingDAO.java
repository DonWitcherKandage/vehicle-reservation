package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import com.megacitycab.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private Connection connection;

    public BookingDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    public void addBooking(Booking booking) {
        String query = "INSERT INTO bookings (destination, date, time, vehicleType, price, status, customerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, booking.getDestination());
            stmt.setString(2, booking.getDate());
            stmt.setString(3, booking.getTime());
            stmt.setString(4, booking.getVehicleType());
            stmt.setDouble(5, booking.getPrice());
            stmt.setString(6, booking.getStatus());
            stmt.setInt(7, booking.getCustomerId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getPendingBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE status = 'PENDING'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("bookingId"),
                        rs.getString("destination"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("vehicleType"),
                        rs.getDouble("price"),
                        rs.getString("status"),
                        rs.getInt("customerId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}