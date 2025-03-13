package com.megacitycab.model;

import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private String customerId;
    private String carId;
    private String driverId;
    private String destination;
    private LocalDateTime bookingDate;
    private double totalAmount;
    private String status;

    public Booking(String bookingId, String customerId, String carId, String driverId, String destination, LocalDateTime bookingDate, double totalAmount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.carId = carId;
        this.driverId = driverId;
        this.destination = destination;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.status = "Pending";
    }

    public String getBookingId() { return bookingId; }
    public String getCustomerId() { return customerId; }
    public String getCarId() { return carId; }
    public String getDriverId() { return driverId; }
    public String getDestination() { return destination; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}