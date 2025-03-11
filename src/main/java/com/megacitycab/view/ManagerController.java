package com.megacitycab.controller;

import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Vehicle;
import com.megacitycab.model.Driver;

public class ManagerController {
    private VehicleDAO vehicleDAO;
    private DriverDAO driverDAO;

    public ManagerController() {
        this.vehicleDAO = new VehicleDAO();
        this.driverDAO = new DriverDAO();
    }

    public void addVehicle(String plateNumber, String type, String model, double ratePerKm) {
        Vehicle vehicle = new Vehicle(plateNumber, type, model, ratePerKm, true);
        vehicleDAO.addVehicle(vehicle);
    }

    public void addDriver(String name, boolean availability) {
        Driver driver = new Driver(name, availability);
        driverDAO.addDriver(driver);
    }

    public void assignDriverToVehicle(String vehicleId, String driverId) {
        driverDAO.assignDriver(vehicleId, driverId);
    }
}
