package com.megacitycab.model;

public class Vehicle {
    private String plateNumber;
    private String type;  // Car, Van, SUV
    private String model;
    private double ratePerKm;
    private boolean availability;

    public Vehicle(String plateNumber, String type, String model, double ratePerKm, boolean availability) {
        this.plateNumber = plateNumber;
        this.type = type;
        this.model = model;
        this.ratePerKm = ratePerKm;
        this.availability = availability;
    }

    // Getters and Setters
    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getRatePerKm() { return ratePerKm; }
    public void setRatePerKm(double ratePerKm) { this.ratePerKm = ratePerKm; }

    public boolean isAvailable() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}