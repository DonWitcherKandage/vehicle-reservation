package com.megacitycab.controller;

import java.util.List;

import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Driver;

public class DriverController {
    private final DriverDAO driverDAO = new DriverDAO();

    // Add a new driver
    public boolean addDriver(String driverID, String name, boolean availability) {
        Driver driver = new Driver(driverID, name, availability);
        return driverDAO.addDriver(driver);
    }

    // Update driver details
    public boolean updateDriver(String driverID, String name, boolean availability) {
        Driver driver = new Driver(driverID, name, availability);
        return driverDAO.updateDriver(driver);
    }

    // Delete a driver
    public boolean deleteDriver(String driverID) {
        return driverDAO.deleteDriver(driverID);
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        return driverDAO.getAllDrivers();
    }
}