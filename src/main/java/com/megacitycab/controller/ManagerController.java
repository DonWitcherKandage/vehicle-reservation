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
        new LoginUI().start(new Stage());
        managerStage.close();
    }

    public void manageVehicles() {
        new ManageVehiclesUI(vehicleController, managerStage).start(new Stage());
        managerStage.hide();
    }

    public void manageDrivers() {
        new ManageDriversUI(managerStage).start(new Stage());
        managerStage.hide();
    }

    public void generateReport() {
        new ReportUI(managerStage).start(new Stage());
        managerStage.hide();
    }
}