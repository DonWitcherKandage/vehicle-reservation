package com.megacitycab.view;

import com.megacitycab.controller.VehicleController;
import com.megacitycab.model.Vehicle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class ManageVehiclesUI extends Application {
    private VehicleController vehicleController = new VehicleController();
    private ObservableList<Vehicle> vehicleList;
    private TableView<Vehicle> vehicleTable;
    private ImageView vehicleImageView;
    private File selectedImageFile; // Stores selected image file

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Vehicles");

        Label titleLabel = new Label("Vehicle Management");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Table to Display Vehicles
        vehicleTable = new TableView<>();
        TableColumn<Vehicle, String> plateColumn = new TableColumn<>("Plate Number");
        plateColumn.setCellValueFactory(data -> data.getValue().plateNumberProperty());

        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> data.getValue().typeProperty());

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(data -> data.getValue().modelProperty());

        TableColumn<Vehicle, Double> rateColumn = new TableColumn<>("Rate per KM");
        rateColumn.setCellValueFactory(data -> data.getValue().ratePerKmProperty().asObject());

        TableColumn<Vehicle, Boolean> availabilityColumn = new TableColumn<>("Available");
        availabilityColumn.setCellValueFactory(data -> data.getValue().availabilityProperty());

        vehicleTable.getColumns().addAll(plateColumn, typeColumn, modelColumn, rateColumn, availabilityColumn);
        loadVehicles(); // Load vehicles from DB

        // Form to Add/Edit Vehicles
        Label plateLabel = new Label("Vehicle Plate:");
        TextField plateField = new TextField();

        Label typeLabel = new Label("Vehicle Type:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Car", "Van", "SUV");

        Label modelLabel = new Label("Vehicle Model:");
        TextField modelField = new TextField();

        Label rateLabel = new Label("Rate per KM:");
        TextField rateField = new TextField();

        Button imageButton = new Button("Upload Image");
        vehicleImageView = new ImageView();
        vehicleImageView.setFitWidth(100);
        vehicleImageView.setPreserveRatio(true);

        imageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg"));
            selectedImageFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedImageFile != null) {
                Image image = new Image(selectedImageFile.toURI().toString());
                vehicleImageView.setImage(image);
            }
        });

        Button addButton = new Button("Add Vehicle");
        Button updateButton = new Button("Update Vehicle");
        Button deleteButton = new Button("Delete Vehicle");

        // Add New Vehicle
        addButton.setOnAction(e -> {
            if (!plateField.getText().isEmpty() && !typeBox.getValue().isEmpty() &&
                    !modelField.getText().isEmpty() && !rateField.getText().isEmpty()) {
                double rate = Double.parseDouble(rateField.getText());
                String imagePath = (selectedImageFile != null) ? selectedImageFile.getAbsolutePath() : "";
                vehicleController.addVehicle(plateField.getText(), typeBox.getValue(), modelField.getText(), rate, imagePath);
                loadVehicles(); // Refresh Table
            }
        });

        // Delete Selected Vehicle
        deleteButton.setOnAction(e -> {
            Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
            if (selectedVehicle != null) {
                vehicleController.deleteVehicle(selectedVehicle.getPlateNumber());
                loadVehicles();
            }
        });

        // Layouts
        HBox formLayout = new HBox(10, plateLabel, plateField, typeLabel, typeBox, modelLabel, modelField, rateLabel, rateField, imageButton, vehicleImageView);
        VBox layout = new VBox(10, titleLabel, vehicleTable, formLayout, addButton, updateButton, deleteButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Load Vehicles from Database
    private void loadVehicles() {
        List<Vehicle> vehicles = vehicleController.getAllVehicles();
        vehicleList = FXCollections.observableArrayList(vehicles);
        vehicleTable.setItems(vehicleList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}