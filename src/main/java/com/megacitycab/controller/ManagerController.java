package com.megacitycab.controller;

import com.megacitycab.view.LoginUI;
import com.megacitycab.view.ManageVehiclesUI;
import com.megacitycab.view.ManageDriversUI;
import com.megacitycab.view.ReportUI;
import javafx.stage.Stage;

/**
 * Controls manager actions like managing vehicles, drivers, and reports.
 */
public class ManagerController {
    
    private VehicleController vehicleController;

    public ManagerController() {
        this.vehicleController = new VehicleController();
    }

    public void logout(Stage primaryStage) {
        // Redirect to Login Screen
        new LoginUI().start(primaryStage);
    }

    public void manageVehicles(Stage primaryStage) {
        // ✅ Open Manage Vehicles UI with VehicleController
        new ManageVehiclesUI(vehicleController).start(primaryStage);
    }

    public void manageDrivers(Stage primaryStage) {
        // Open Manage Drivers UI
        new ManageDriversUI().start(primaryStage);
    }

    public void generateReport(Stage primaryStage) {
        // Open Report Generation UI
        new ReportUI().start(primaryStage);
    }
}