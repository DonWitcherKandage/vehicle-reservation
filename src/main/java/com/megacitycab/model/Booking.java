package com.megacitycab.model;

public class Booking {
    private int bookingId;
    private String destination;
    private String date;
    private String time;
    private String vehicleType;
    private double price;
    private String status;
    private int customerId;

    // Constructor
    public Booking(int bookingId, String destination, String date, String time, String vehicleType, double price, String status, int customerId) {
        this.bookingId = bookingId;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.vehicleType = vehicleType;
        this.price = price;
        this.status = status;
        this.customerId = customerId;
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public String getDestination() { return destination; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getVehicleType() { return vehicleType; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    public int getCustomerId() { return customerId; }
}