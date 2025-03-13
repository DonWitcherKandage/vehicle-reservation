package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import com.megacitycab.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // ✅ Add a new booking to the database
    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (bookingId, customerId, carId, driverId, destination, bookingDate, totalAmount, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingId());
            stmt.setString(2, booking.getCustomerId());
            stmt.setString(3, booking.getCarId());
            stmt.setString(4, booking.getDriverId());
            stmt.setString(5, booking.getDestination());
            stmt.setTimestamp(6, Timestamp.valueOf(booking.getBookingDate()));
            stmt.setDouble(7, booking.getTotalAmount());
            stmt.setString(8, booking.getStatus());

            stmt.executeUpdate();
            System.out.println("✅ Booking added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Update the status of an existing booking
    public void updateBookingStatus(String bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE bookingId = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setString(2, bookingId);

            stmt.executeUpdate();
            System.out.println("✅ Booking status updated to: " + status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Retrieve a booking by its ID
    public Booking getBookingById(String bookingId) {
        String sql = "SELECT * FROM bookings WHERE bookingId = ?";
        Booking booking = null;

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                        rs.getString("bookingId"),
                        rs.getString("customerId"),
                        rs.getString("carId"),
                        rs.getString("driverId"),
                        rs.getString("destination"),
                        rs.getTimestamp("bookingDate").toLocalDateTime(),
                        rs.getDouble("totalAmount")
                );
                booking.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    // ✅ Retrieve all bookings for a specific customer
    public List<Booking> getBookingsByCustomer(String customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customerId = ? ORDER BY bookingDate DESC";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getString("bookingId"),
                        rs.getString("customerId"),
                        rs.getString("carId"),
                        rs.getString("driverId"),
                        rs.getString("destination"),
                        rs.getTimestamp("bookingDate").toLocalDateTime(),
                        rs.getDouble("totalAmount")
                );
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}