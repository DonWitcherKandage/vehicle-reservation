package com.megacitycab.controller;

import com.megacitycab.dao.DriverDAO;
import com.megacitycab.dao.VehicleDAO;
import com.megacitycab.model.Driver;
import com.megacitycab.model.Vehicle;
import com.megacitycab.util.DatabaseManager;

import java.util.List;

/**
 * Controller for managing vehicles and drivers in the system.
 */
public class ManagerController {
    private final DriverDAO driverDAO;
    private final VehicleDAO vehicleDAO;

    // ✅ Correctly initializing DAOs
    public ManagerController() {
        this.driverDAO = new DriverDAO();
        this.vehicleDAO = new VehicleDAO(DatabaseManager.getInstance().getConnection()); // ✅ FIXED
    }

    /**
     * ✅ Adds a new driver to the system.
     */
    public boolean addDriver(String driverId, String name, boolean availability) {
        Driver driver = new Driver(driverId, name, availability);
        return driverDAO.addDriver(driver);
    }

    /**
     * ✅ Assigns a driver to a vehicle.
     */
    public boolean assignDriver(String driverId, String vehicleId) {
        return driverDAO.assignDriver(driverId, vehicleId);
    }

    /**
     * ✅ Adds a new vehicle to the system.
     */
    public boolean addVehicle(String plateNumber, String type, String model, double ratePerKm, boolean availability, String imagePath) {
        Vehicle vehicle = new Vehicle(plateNumber, type, model, ratePerKm, availability, imagePath);
        return vehicleDAO.addVehicle(vehicle);
    }

    /**
     * ✅ Deletes a vehicle from the system.
     */
    public boolean deleteVehicle(String plateNumber) {
        return vehicleDAO.deleteVehicle(plateNumber); // ✅ FIXED
    }

    /**
     * ✅ Gets a list of all available drivers.
     */
    public List<Driver> getAvailableDrivers() {
        return driverDAO.getAllDrivers();
    }

    /**
     * ✅ Gets a list of all vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }
}