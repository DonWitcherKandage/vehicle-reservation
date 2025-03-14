package com.megacitycab.controller;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.model.Vehicle;
import javafx.collections.ObservableList;

/**
 * VehicleController - Handles vehicle CRUD operations via DAO.
 */
public class VehicleController {
    private final VehicleDAO vehicleDAO;

    public VehicleController() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void addVehicle(String model, String plateNumber, String type, double ratePerKm, boolean availability, String imagePath) {
        vehicleDAO.addVehicle(model, plateNumber, type, ratePerKm, availability, imagePath);
    }

    public ObservableList<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    public void deleteVehicle(String vehicleId) {
        vehicleDAO.deleteVehicle(vehicleId);
    }

    public void updateVehicle(String vehicleId, boolean availability) {
        vehicleDAO.updateVehicle(vehicleId, availability);
    }
}