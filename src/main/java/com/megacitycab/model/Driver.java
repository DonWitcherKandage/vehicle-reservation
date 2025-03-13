package com.megacitycab.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;

/**
 * Represents a driver in the MegaCityCab system.
 */
public class Driver {
    private final StringProperty driverId;
    private final StringProperty name;
    private final BooleanProperty availability;

    // ✅ Correct Constructor
    public Driver(String driverId, String name, boolean availability) {
        this.driverId = new SimpleStringProperty(driverId);
        this.name = new SimpleStringProperty(name);
        this.availability = new SimpleBooleanProperty(availability);
    }

    // ✅ Getter methods
    public String getDriverId() { return driverId.get(); }
    public String getName() { return name.get(); }
    public boolean isAvailable() { return availability.get(); }

    // ✅ JavaFX Property Getters (Fixes the errors in ⁠ ManageDriversUI.java ⁠)
    public StringProperty driverIdProperty() { return driverId; }
    public StringProperty nameProperty() { return name; }
    public BooleanProperty availabilityProperty() { return availability; }

    // ✅ Setter methods
    public void setDriverId(String driverId) { this.driverId.set(driverId); }
    public void setName(String name) { this.name.set(name); }
    public void setAvailability(boolean availability) { this.availability.set(availability); }
}