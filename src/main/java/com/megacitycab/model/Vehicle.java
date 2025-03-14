package com.megacitycab.model;

import javafx.beans.property.*;

public class Vehicle {
    private final StringProperty id;
    private final StringProperty model;
    private final StringProperty plateNumber;
    private final StringProperty type;
    private final DoubleProperty ratePerKm;
    private final BooleanProperty availability;
    private final StringProperty imagePath;

    public Vehicle(String id, String model, String plateNumber, String type, double ratePerKm, boolean availability, String imagePath) {
        this.id = new SimpleStringProperty(id);
        this.model = new SimpleStringProperty(model);
        this.plateNumber = new SimpleStringProperty(plateNumber);
        this.type = new SimpleStringProperty(type);
        this.ratePerKm = new SimpleDoubleProperty(ratePerKm);
        this.availability = new SimpleBooleanProperty(availability);
        this.imagePath = new SimpleStringProperty(imagePath);
    }

    public StringProperty idProperty() { return id; }
    public StringProperty modelProperty() { return model; }
    public StringProperty plateNumberProperty() { return plateNumber; }
    public StringProperty typeProperty() { return type; }
    public DoubleProperty ratePerKmProperty() { return ratePerKm; }
    public BooleanProperty availabilityProperty() { return availability; }
    public StringProperty imagePathProperty() { return imagePath; }

    public String getId() { return id.get(); }
    public String getModel() { return model.get(); }
    public String getPlateNumber() { return plateNumber.get(); }
    public String getType() { return type.get(); }
    public double getRatePerKm() { return ratePerKm.get(); }
    public boolean isAvailable() { return availability.get(); }
    public String getImagePath() { return imagePath.get(); }
}

