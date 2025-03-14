package com.megacitycab.model;

public class Vehicle {
    private String id;
    private String model;
    private String plateNumber;
    private double ratePerKm;
    private boolean isAvailable;
    private String imagePath;

    // Constructor
    public Vehicle(String id, String model, String plateNumber, double ratePerKm, boolean isAvailable, String imagePath) {
        this.id = id;
        this.model = model;
        this.plateNumber = plateNumber;
        this.ratePerKm = ratePerKm;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}