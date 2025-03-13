package com.megacitycab.controller;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import com.megacitycab.util.BookingNotifier;
import com.megacitycab.util.Observer;
import com.megacitycab.util.FareCalculator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingController {
    private final BookingDAO bookingDAO;
    private final BookingNotifier notifier;

    public BookingController() {
        this.bookingDAO = new BookingDAO();
        this.notifier = new BookingNotifier();
    }

    // ✅ Create a new booking
    public void createBooking(String customerId, String carId, String driverId, String destination, double distanceKm) {
        if (customerId == null || carId == null || driverId == null || destination == null || distanceKm <= 0) {
            throw new IllegalArgumentException("Invalid booking details provided!");
        }

        String bookingId = UUID.randomUUID().toString();
        double totalAmount = FareCalculator.calculateFare(carId, distanceKm);
        
        Booking booking = new Booking(bookingId, customerId, carId, driverId, destination, LocalDateTime.now(), totalAmount);
        bookingDAO.addBooking(booking);

        // Notify observers (customers & managers)
        notifier.notifyObservers("📢 New Booking Created: " + bookingId + " for Customer: " + customerId);
    }

    // ✅ Update booking status (Accepted, Completed, Canceled)
    public void updateBookingStatus(String bookingId, String status) {
        if (bookingId == null || status == null) {
            throw new IllegalArgumentException("Invalid booking ID or status!");
        }

        bookingDAO.updateBookingStatus(bookingId, status);
        notifier.notifyObservers("🚖 Booking " + bookingId + " status changed to: " + status);
    }

    // ✅ Get Booking Details
    public Booking getBookingDetails(String bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    // ✅ Get all bookings for a customer
    public List<Booking> getCustomerBookings(String customerId) {
        return bookingDAO.getBookingsByCustomer(customerId);
    }

    // ✅ Register an Observer (Customer, Manager) for real-time updates
    public void addBookingObserver(Observer observer) {
        notifier.addObserver(observer);
    }
}