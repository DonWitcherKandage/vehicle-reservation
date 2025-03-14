package com.megacitycab.model;

import javafx.beans.property.*;

public class Vehicle {
    private final StringProperty plateNumber;
    private final StringProperty type;
    private final StringProperty model;
    private final DoubleProperty ratePerKm;
    private final BooleanProperty availability;
    private final StringProperty imagePath;

    public Vehicle(String plateNumber, String type, String model, double ratePerKm, boolean availability, String imagePath) {
        this.plateNumber = new SimpleStringProperty(plateNumber);
        this.type = new SimpleStringProperty(type);
        this.model = new SimpleStringProperty(model);
        this.ratePerKm = new SimpleDoubleProperty(ratePerKm);
        this.availability = new SimpleBooleanProperty(availability);
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    // Property Getters for JavaFX Bindings
    public StringProperty plateNumberProperty() { return plateNumber; }
    public StringProperty typeProperty() { return type; }
    public StringProperty modelProperty() { return model; }
    public DoubleProperty ratePerKmProperty() { return ratePerKm; }
    public BooleanProperty availabilityProperty() { return availability; }
    public StringProperty imagePathProperty() { return imagePath; }

    // Regular Getters & Setters
    public String getPlateNumber() { return plateNumber.get(); }
    public void setPlateNumber(String value) { plateNumber.set(value); }

    public String getType() { return type.get(); }
    public void setType(String value) { type.set(value); }

    public String getModel() { return model.get(); }
    public void setModel(String value) { model.set(value); }

    public double getRatePerKm() { return ratePerKm.get(); }
    public void setRatePerKm(double value) { ratePerKm.set(value); }

    public boolean isAvailable() { return availability.get(); }
    public void setAvailability(boolean value) { availability.set(value); }

    public String getImagePath() { return imagePath.get(); }
    public void setImagePath(String value) { imagePath.set(value); }
}

