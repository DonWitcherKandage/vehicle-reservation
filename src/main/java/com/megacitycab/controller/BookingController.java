package com.megacitycab.controller;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;

import java.util.List;

public class BookingController {
    private BookingDAO bookingDAO;

    public BookingController() {
        this.bookingDAO = new BookingDAO();
    }

    public void addBooking(String destination, String date, String time, String vehicleType, double price, int customerId) {
        Booking booking = new Booking(0, destination, date, time, vehicleType, price, "PENDING", customerId);
        bookingDAO.addBooking(booking);
    }

    public List<Booking> getPendingBookings() {
        return bookingDAO.getPendingBookings();
    }
}