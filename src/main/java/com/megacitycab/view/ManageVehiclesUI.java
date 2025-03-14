package com.megacitycab.view;

import com.megacitycab.controller.VehicleController;
import com.megacitycab.model.Vehicle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * UI for managing vehicles.
 */
public class ManageVehiclesUI extends Application {
    private VehicleController vehicleController = new VehicleController();
    private TableView<Vehicle> vehicleTable;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Vehicles");

        Label titleLabel = new Label("Vehicle Management");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // ✅ Table View Setup
        vehicleTable = new TableView<>();
        TableColumn<Vehicle, String> plateColumn = new TableColumn<>("Plate Number");
        plateColumn.setCellValueFactory(data -> data.getValue().plateNumberProperty());

        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> data.getValue().typeProperty());

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(data -> data.getValue().modelProperty());

        TableColumn<Vehicle, Number> rateColumn = new TableColumn<>("Rate/km");
        rateColumn.setCellValueFactory(data -> data.getValue().ratePerKmProperty());

        TableColumn<Vehicle, Boolean> availabilityColumn = new TableColumn<>("Available");
        availabilityColumn.setCellValueFactory(data -> data.getValue().availabilityProperty());

        vehicleTable.getColumns().add(plateColumn);
        vehicleTable.getColumns().add(typeColumn);
        vehicleTable.getColumns().add(modelColumn);
        
        // ✅ Load Vehicles
        loadVehicles();

        // ✅ Layout Setup
        VBox layout = new VBox(15, titleLabel, vehicleTable);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadVehicles() {
        ObservableList<Vehicle> vehicleList = vehicleController.getVehicles();
        vehicleTable.setItems(vehicleList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}