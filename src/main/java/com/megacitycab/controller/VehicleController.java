package com.megacitycab.controller;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.model.Vehicle;
import javafx.collections.ObservableList;

public class VehicleController {
    private final VehicleDAO vehicleDAO;

    public VehicleController() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleDAO.addVehicle(
            vehicle.getModel(),
            vehicle.getPlateNumber(),
            vehicle.getType(),
            vehicle.getRatePerKm(),
            vehicle.isAvailable(),
            vehicle.getImagePath()
        );
    }

    public ObservableList<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    public void deleteVehicle(String vehicleId) {
        vehicleDAO.deleteVehicle(vehicleId);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleDAO.updateVehicle(vehicle);
    }

    public void updateVehicleAvailability(String vehicleId, boolean availability) {
        vehicleDAO.updateVehicleAvailability(vehicleId, availability);
    }
}