package com.megacitycab.controller;

import com.megacitycab.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controls vehicle-related operations.
 */
public class VehicleController {
    private ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();

    // ✅ Add a vehicle using parameters
    public void addVehicle(String plateNumber, String type, String model, double ratePerKm, boolean availability, String imagePath) {
        vehicles.add(new Vehicle(plateNumber, type, model, ratePerKm, availability, imagePath));
    }

    // ✅ Overloaded method to add a Vehicle object
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // ✅ Delete a vehicle
    public void deleteVehicle(String plateNumber) {
        vehicles.removeIf(vehicle -> vehicle.getPlateNumber().equals(plateNumber));
    }

    // ✅ Get all vehicles
    public ObservableList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    // ✅ Update vehicle availability
    public void updateVehicle(String plateNumber, boolean availability) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                vehicle.setAvailability(availability);
                break;
            }
        }
    }
}
 