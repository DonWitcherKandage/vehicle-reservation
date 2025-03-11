package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.megacitycab.controller.ManagerController;

public class ManageVehiclesUI extends Application {
    private ManagerController managerController = new ManagerController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Vehicles");

        Label plateLabel = new Label("Vehicle Number Plate:");
        TextField plateField = new TextField();

        Label typeLabel = new Label("Vehicle Type:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Car", "Van", "SUV");

        Label modelLabel = new Label("Vehicle Model:");
        TextField modelField = new TextField();

        Label priceLabel = new Label("Rate Per KM:");
        TextField priceField = new TextField();

        Button addButton = new Button("Add Vehicle");
        addButton.setOnAction(e -> {
            managerController.addVehicle(plateField.getText(), typeBox.getValue(), modelField.getText(), Double.parseDouble(priceField.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vehicle Added Successfully!");
            alert.show();
        });

        VBox layout = new VBox(10, plateLabel, plateField, typeLabel, typeBox, modelLabel, modelField, priceLabel, priceField, addButton);
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}