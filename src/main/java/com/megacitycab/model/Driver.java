package com.megacitycab.model;

public class Driver {
    private String driverId;
    private String name;
    private boolean availability;

    public Driver(String driverId, String name, boolean availability) {
        this.driverId = driverId;
        this.name = name;
        this.availability = availability;
    }

    // Getters and Setters
    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isAvailable() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}