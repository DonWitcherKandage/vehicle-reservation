package com.megacitycab.controller;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.model.Vehicle;

import java.util.List;

public class VehicleController {
    private VehicleDAO vehicleDAO;

    public VehicleController() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void addVehicle(String plateNumber, String type, String model, double ratePerKm, String imagePath) {
        Vehicle vehicle = new Vehicle(plateNumber, type, model, ratePerKm, true, imagePath);
        vehicleDAO.addVehicle(vehicle);
    }

    public void deleteVehicle(String plateNumber) {
        vehicleDAO.deleteVehicle(plateNumber);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }
}