package com.megacitycab.controller;

import com.megacitycab.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controls vehicle-related operations.
 */
public class VehicleController {
    private ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();

    // ✅ Add a vehicle
    public void addVehicle(String plateNumber, String type, String model, double ratePerKm, boolean availability, String imagePath) {
        vehicles.add(new Vehicle(plateNumber, type, model, ratePerKm, availability, imagePath));
    }

    // ✅ Delete a vehicle
    public void deleteVehicle(String plateNumber) {
        vehicles.removeIf(vehicle -> vehicle.getPlateNumber().equals(plateNumber));
    }

    // ✅ Get all vehicles
    public ObservableList<Vehicle> getVehicles() {
        return vehicles;
    }
}