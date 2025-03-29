package com.megacitycab.controller;

import com.megacitycab.view.LoginUI;
import com.megacitycab.view.ManageVehiclesUI;
import com.megacitycab.view.ManageDriversUI;
import com.megacitycab.view.ReportUI;
import javafx.stage.Stage;

public class ManagerController {
    private final VehicleController vehicleController;
    private final Stage managerStage;

    public ManagerController(Stage managerStage) {
        this.managerStage = managerStage;
        this.vehicleController = new VehicleController();
    }

    public void logout() {
        managerStage.close(); // Close first, then create new login UI
        new LoginUI("MANAGER").start(new Stage());
    }

    public void manageVehicles() {
        managerStage.hide(); // Hide before creating new UI
        Stage vehicleStage = new Stage();
        vehicleStage.setOnCloseRequest(e -> {
            managerStage.show();
            managerStage.toFront();
        });
        new ManageVehiclesUI(vehicleController, managerStage).start(vehicleStage);
    }

    public void manageDrivers() {
        managerStage.hide(); // Hide before creating new UI
        Stage driversStage = new Stage();
        driversStage.setOnCloseRequest(e -> {
            managerStage.show();
            managerStage.toFront();
        });
        new ManageDriversUI(managerStage).start(driversStage);
    }

    public void generateReport() {
        managerStage.hide(); // Hide before creating new UI
        Stage reportStage = new Stage();
        reportStage.setOnCloseRequest(e -> {
            managerStage.show();
            managerStage.toFront();
        });
        new ReportUI(managerStage).start(reportStage);
    }
}