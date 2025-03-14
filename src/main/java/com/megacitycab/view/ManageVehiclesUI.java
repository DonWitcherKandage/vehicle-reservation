package com.megacitycab.view;

import com.megacitycab.controller.VehicleController;
import com.megacitycab.model.Vehicle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX UI for managing vehicles.
 */
public class ManageVehiclesUI {
    private VehicleController vehicleController;
    private ObservableList<Vehicle> vehicleList;
    private TableView<Vehicle> vehicleTable;

    public ManageVehiclesUI(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
        this.vehicleList = FXCollections.observableArrayList(vehicleController.getAllVehicles());
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Vehicles");

        Label title = new Label("Manage Vehicles");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // TableView setup
        vehicleTable = new TableView<>();
        setupTableColumns();
        vehicleTable.setItems(vehicleList);

        // Input Fields
        TextField plateField = new TextField();
        plateField.setPromptText("Plate Number");

        TextField typeField = new TextField();
        typeField.setPromptText("Type");

        TextField modelField = new TextField();
        modelField.setPromptText("Model");

        TextField rateField = new TextField();
        rateField.setPromptText("Rate per Km");

        CheckBox availabilityCheckBox = new CheckBox("Available");

        TextField imagePathField = new TextField();
        imagePathField.setPromptText("Image Path");

        // Buttons
        Button addVehicleBtn = new Button("➕ Add Vehicle");
        Button updateVehicleBtn = new Button("✏️ Update Availability");
        Button deleteVehicleBtn = new Button("❌ Delete Vehicle");

        // Button Actions
        addVehicleBtn.setOnAction(e -> addVehicle(plateField, typeField, modelField, rateField, availabilityCheckBox, imagePathField));
        updateVehicleBtn.setOnAction(e -> updateVehicleAvailability());
        deleteVehicleBtn.setOnAction(e -> deleteVehicle());

        // Layout
        HBox inputBox = new HBox(10, plateField, typeField, modelField, rateField, availabilityCheckBox, imagePathField, addVehicleBtn);
        inputBox.setPadding(new Insets(10));

        HBox buttonBox = new HBox(10, updateVehicleBtn, deleteVehicleBtn);
        buttonBox.setPadding(new Insets(10));

        VBox layout = new VBox(15, title, vehicleTable, inputBox, buttonBox);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @SuppressWarnings("unchecked")
    private void setupTableColumns() {
        TableColumn<Vehicle, String> plateColumn = new TableColumn<>("Plate Number");
        plateColumn.setCellValueFactory(cellData -> cellData.getValue().plateNumberProperty());
    
        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    
        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
    
        TableColumn<Vehicle, Double> rateColumn = new TableColumn<>("Rate per Km");
        rateColumn.setCellValueFactory(cellData -> cellData.getValue().ratePerKmProperty().asObject());
    
        TableColumn<Vehicle, Boolean> availabilityColumn = new TableColumn<>("Availability");
        availabilityColumn.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());
    
        // ✅ Corrected version without type safety warning
        vehicleTable.getColumns().addAll(plateColumn, typeColumn, modelColumn, rateColumn, availabilityColumn);
    }
    

    // Add a new vehicle
    private void addVehicle(TextField plateField, TextField typeField, TextField modelField, TextField rateField, CheckBox availabilityCheckBox, TextField imagePathField) {
        try {
            String plateNumber = plateField.getText();
            String type = typeField.getText();
            String model = modelField.getText();
            double ratePerKm = Double.parseDouble(rateField.getText());
            boolean availability = availabilityCheckBox.isSelected();
            String imagePath = imagePathField.getText();

            Vehicle newVehicle = new Vehicle(plateNumber, type, model, ratePerKm, availability, imagePath);
            vehicleController.addVehicle(newVehicle);
            refreshTable();

            // Clear input fields after adding
            plateField.clear();
            typeField.clear();
            modelField.clear();
            rateField.clear();
            availabilityCheckBox.setSelected(false);
            imagePathField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Rate per Km must be a valid number.");
        }
    }

    // Delete selected vehicle
    private void deleteVehicle() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            vehicleController.deleteVehicle(selected.getPlateNumber());
            vehicleList.remove(selected);
        } else {
            showAlert("No Selection", "Please select a vehicle to delete.");
        }
    }

    // Update vehicle availability
    private void updateVehicleAvailability() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean newAvailability = !selected.isAvailable();
            vehicleController.updateVehicle(selected.getPlateNumber(), newAvailability);
            refreshTable();
        } else {
            showAlert("No Selection", "Please select a vehicle to update.");
        }
    }

    // Refresh TableView data
    private void refreshTable() {
        Platform.runLater(() -> {
            vehicleList.setAll(vehicleController.getAllVehicles());
        });
    }

    // Show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
