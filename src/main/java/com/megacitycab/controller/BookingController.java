package com.megacitycab.controller;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import com.megacitycab.util.BookingNotifier;
import com.megacitycab.util.Observer;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingController {
    private BookingDAO bookingDAO;
    private BookingNotifier notifier;

    public BookingController() {
        this.bookingDAO = new BookingDAO();
        this.notifier = new BookingNotifier();
    }

    public void createBooking(String customerId, String carId, String driverId, String destination, double totalAmount) {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, customerId, carId, driverId, destination, LocalDateTime.now(), totalAmount);
        
        bookingDAO.addBooking(booking);
        notifier.notifyObservers("New booking created: " + booking.getBookingId());
    }

    public void updateBookingStatus(String bookingId, String status) {
        bookingDAO.updateBookingStatus(bookingId, status);
        notifier.notifyObservers("Booking " + bookingId + " status changed to " + status);
    }

    public void addBookingObserver(Observer observer) {
        notifier.addObserver(observer);
    }
}