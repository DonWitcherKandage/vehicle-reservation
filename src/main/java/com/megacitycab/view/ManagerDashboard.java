package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manager Dashboard");

        Label welcomeLabel = new Label("Welcome, Manager!");

        Button manageVehiclesBtn = new Button("Manage Vehicles");
        Button manageDriversBtn = new Button("Manage Drivers");
        Button viewBookingsBtn = new Button("View Bookings");
        Button generateReportsBtn = new Button("Generate Reports");

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> {
            LoginUI loginUI = new LoginUI();
            loginUI.start(new Stage());  // Open Login Screen
            primaryStage.close();  // Close Manager Dashboard
        });

        VBox layout = new VBox(15, welcomeLabel, manageVehiclesBtn, manageDriversBtn, viewBookingsBtn, generateReportsBtn, logoutButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}