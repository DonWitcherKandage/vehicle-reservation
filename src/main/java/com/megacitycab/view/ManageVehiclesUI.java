package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.megacitycab.model.Vehicle;

public class ManageVehiclesUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Table to display vehicles
        TableView<Vehicle> vehicleTable = new TableView<>();

        // Buttons
        Button addVehicleButton = new Button("Add Vehicle");
        Button editVehicleButton = new Button("Edit Vehicle");
        Button deleteVehicleButton = new Button("Delete Vehicle");

        // Add components to layout
        root.getChildren().addAll(vehicleTable, addVehicleButton, editVehicleButton, deleteVehicleButton);

        // Event Handlers
        addVehicleButton.setOnAction(e -> {
            // Open Add Vehicle Dialog
        });

        editVehicleButton.setOnAction(e -> {
            // Open Edit Vehicle Dialog
        });

        deleteVehicleButton.setOnAction(e -> {
            // Delete Selected Vehicle
        });

        // Set Scene
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Manage Vehicles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}