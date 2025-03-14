package com.megacitycab.view;

import com.megacitycab.controller.ManagerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Manager Dashboard UI.
 */
public class ManagerDashboard extends Application {

    private ManagerController managerController;

    public ManagerDashboard() {
        this.managerController = new ManagerController();
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem logoutItem = new MenuItem("Logout");
        fileMenu.getItems().add(logoutItem);

        Menu manageMenu = new Menu("Manage");
        MenuItem manageVehiclesItem = new MenuItem("Manage Vehicles");
        MenuItem manageDriversItem = new MenuItem("Manage Drivers");
        manageMenu.getItems().addAll(manageVehiclesItem, manageDriversItem);

        Menu reportsMenu = new Menu("Reports");
        MenuItem generateReportItem = new MenuItem("Generate Report");
        reportsMenu.getItems().add(generateReportItem);

        menuBar.getMenus().addAll(fileMenu, manageMenu, reportsMenu);
        root.setTop(menuBar);

        // Event Handlers
        logoutItem.setOnAction(e -> managerController.logout(primaryStage));
        manageVehiclesItem.setOnAction(e -> managerController.manageVehicles(primaryStage));
        manageDriversItem.setOnAction(e -> managerController.manageDrivers(primaryStage));
        generateReportItem.setOnAction(e -> managerController.generateReport(primaryStage));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Manager Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane getRoot() {
        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();

        Menu manageMenu = new Menu("Manage");
        MenuItem manageVehiclesItem = new MenuItem("Manage Vehicles");
        manageMenu.getItems().add(manageVehiclesItem);

        menuBar.getMenus().addAll(manageMenu);
        root.setTop(menuBar);

        return root;
    }
}
