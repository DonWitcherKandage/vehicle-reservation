package com.megacitycab.view;

import com.megacitycab.controller.VehicleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Manager Dashboard UI.
 */
public class ManagerDashboard extends Application {

    private VehicleController vehicleController;

    public ManagerDashboard() {
        this.vehicleController = new VehicleController();
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create Menu Bar
        MenuBar menuBar = new MenuBar();

        // File Menu
        Menu fileMenu = new Menu("File");
        MenuItem logoutItem = new MenuItem("Logout");
        fileMenu.getItems().add(logoutItem);

        // Manage Menu
        Menu manageMenu = new Menu("Manage");
        MenuItem manageVehiclesItem = new MenuItem("Manage Vehicles");
        MenuItem manageDriversItem = new MenuItem("Manage Drivers");
        manageMenu.getItems().addAll(manageVehiclesItem, manageDriversItem);

        // Reports Menu
        Menu reportsMenu = new Menu("Reports");
        MenuItem generateReportItem = new MenuItem("Generate Report");
        reportsMenu.getItems().add(generateReportItem);

        // Add Menus to MenuBar
        menuBar.getMenus().addAll(fileMenu, manageMenu, reportsMenu);

        // Set MenuBar to Top of BorderPane
        root.setTop(menuBar);

        // Event Handlers
        logoutItem.setOnAction(e -> {
            // Redirect to Login Screen
            new LoginUI().start(primaryStage);
        });

        manageVehiclesItem.setOnAction(e -> {
            // Open Manage Vehicles UI (Pass `vehicleController`)
            ManageVehiclesUI manageVehiclesUI = new ManageVehiclesUI(vehicleController);
            manageVehiclesUI.start(primaryStage);
        });

        manageDriversItem.setOnAction(e -> {
            // Open Manage Drivers UI
            new ManageDriversUI().start(primaryStage);
        });

        generateReportItem.setOnAction(e -> {
            // Open Report Generation UI
            new ReportUI().start(primaryStage);
        });

        // Set Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Manager Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}