package com.megacitycab.model;

import javafx.beans.property.*;

/**
 * Represents a vehicle in the MegaCityCab system.
 */
public class Vehicle {
    private final StringProperty plateNumber;
    private final StringProperty type;
    private final StringProperty model;
    private final DoubleProperty ratePerKm;
    private final BooleanProperty availability;
    private final StringProperty imagePath;

    // ✅ Correct Constructor
    public Vehicle(String plateNumber, String type, String model, double ratePerKm, boolean availability, String imagePath) {
        this.plateNumber = new SimpleStringProperty(plateNumber);
        this.type = new SimpleStringProperty(type);
        this.model = new SimpleStringProperty(model);
        this.ratePerKm = new SimpleDoubleProperty(ratePerKm);
        this.availability = new SimpleBooleanProperty(availability);
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    // ✅ Getter methods
    public String getPlateNumber() { return plateNumber.get(); }
    public String getType() { return type.get(); }
    public String getModel() { return model.get(); }
    public double getRatePerKm() { return ratePerKm.get(); }
    public boolean isAvailable() { return availability.get(); }
    public String getImagePath() { return imagePath.get(); }

    // ✅ JavaFX Property Getters (Fixes ManageVehiclesUI.java errors)
    public StringProperty plateNumberProperty() { return plateNumber; }
    public StringProperty typeProperty() { return type; }
    public StringProperty modelProperty() { return model; }
    public DoubleProperty ratePerKmProperty() { return ratePerKm; }
    public BooleanProperty availabilityProperty() { return availability; }
    public StringProperty imagePathProperty() { return imagePath; }

    // ✅ Setter methods
    public void setPlateNumber(String plateNumber) { this.plateNumber.set(plateNumber); }
    public void setType(String type) { this.type.set(type); }
    public void setModel(String model) { this.model.set(model); }
    public void setRatePerKm(double ratePerKm) { this.ratePerKm.set(ratePerKm); }
    public void setAvailability(boolean availability) { this.availability.set(availability); }
    public void setImagePath(String imagePath) { this.imagePath.set(imagePath); }
}