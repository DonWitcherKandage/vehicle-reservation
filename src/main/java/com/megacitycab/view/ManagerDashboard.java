package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerDashboard extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label welcomeLabel = new Label("Manager Dashboard");
        Button manageVehiclesBtn = new Button("Manage Vehicles");
        Button manageDriversBtn = new Button("Manage Drivers");
        Button viewReportsBtn = new Button("View Reports");
        Button logoutBtn = new Button("Logout");

        manageVehiclesBtn.setOnAction(e -> {
            ManageVehiclesUI vehiclesUI = new ManageVehiclesUI();
            vehiclesUI.start(new Stage());
        });

        VBox layout = new VBox(15, welcomeLabel, manageVehiclesBtn, manageDriversBtn, viewReportsBtn, logoutBtn);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manager Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}