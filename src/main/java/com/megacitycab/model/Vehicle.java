package com.megacitycab.model;

import javafx.beans.property.*;

public class Vehicle {
    private final StringProperty id;
    private final StringProperty model;
    private final StringProperty plateNumber;
    private final StringProperty type;
    private final DoubleProperty ratePerKm;
    private final BooleanProperty available;  // Changed from availability to available
    private final StringProperty imagePath;

    public Vehicle(String id, String model, String plateNumber, String type, 
                  double ratePerKm, boolean available, String imagePath) {
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.plateNumber = new SimpleStringProperty(plateNumber);
        this.type = new SimpleStringProperty(type);
        this.ratePerKm = new SimpleDoubleProperty(ratePerKm);
        this.available = new SimpleBooleanProperty(available);  // Changed to available
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    // Property getters (for JavaFX binding)
    public StringProperty idProperty() { return id; }
    public StringProperty modelProperty() { return model; }
    public StringProperty plateNumberProperty() { return plateNumber; }
    public StringProperty typeProperty() { return type; }
    public DoubleProperty ratePerKmProperty() { return ratePerKm; }
    public BooleanProperty availableProperty() { return available; }  // Changed to availableProperty
    public StringProperty imagePathProperty() { return imagePath; }

    // Regular getters
    public String getId() { return id.get(); }
    public String getModel() { return model.get(); }
    public String getPlateNumber() { return plateNumber.get(); }
    public String getType() { return type.get(); }
    public double getRatePerKm() { return ratePerKm.get(); }
    public boolean isAvailable() { return available.get(); }  // Matches table column "available"
    public String getImagePath() { return imagePath.get(); }

    // Setters (if needed for updates)
    public void setModel(String model) { this.model.set(model); }
    public void setPlateNumber(String plateNumber) { this.plateNumber.set(plateNumber); }
    public void setType(String type) { this.type.set(type); }
    public void setRatePerKm(double ratePerKm) { this.ratePerKm.set(ratePerKm); }
    public void setAvailable(boolean available) { this.available.set(available); }
    public void setImagePath(String imagePath) { this.imagePath.set(imagePath); }
}