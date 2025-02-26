package com.megacitycab.model;

import java.time.LocalDateTime;

public class Booking {

    private String bookingId;
    private String customerId;
    private String carId;
    private String driverId;
    private String pickupLocation;
    private String destination;
    private LocalDateTime bookingTime;
    private String status;

    public Booking(String bookingId, String customerId, String carId, String driverId, String destination, String pickupLocation, LocalDateTime bookingTime, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.carId = carId;
        this.driverId = driverId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public String getBookingId() { 
        return bookingId; 
    }
    public String getCustomerId() {
         return customerId; 
        }
    public String getCarId() {
         return carId; 
        }
    public String getDriverId() {
         return driverId; 
        }
    public String getPickupLocation() {
         return pickupLocation; 
        }
    public String getDestination() {
         return destination; 
        }
    public LocalDateTime getBookingTime() {
         return bookingTime; 
        }
    public String getStatus() {
         return status; 
        }
    
}
