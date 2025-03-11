package com.megacitycab.controller;

import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Driver;

import java.util.List;
import java.util.UUID;

public class DriverController {
    private DriverDAO driverDAO;

    public DriverController() {
        this.driverDAO = new DriverDAO();
    }

    public void addDriver(String name, boolean availability) {
        String driverId = UUID.randomUUID().toString(); // Generate unique driver ID
        Driver driver = new Driver(driverId, name, availability);
        driverDAO.addDriver(driver);
    }

    public void deleteDriver(String driverId) {
        driverDAO.deleteDriver(driverId);
    }

    public List<Driver> getAllDrivers() {
        return driverDAO.getAllDrivers();
    }
}