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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

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

        vehicleTable = new TableView<>();
        setupTableColumns();
        vehicleTable.setItems(vehicleList);

        TextField plateField = new TextField();
        plateField.setPromptText("Plate Number (ABC-1234)");

        ComboBox<String> typeComboBox = new ComboBox<>();
        List<String> vehicleTypes = Arrays.asList("Car", "Van", "Truck");
        typeComboBox.setItems(FXCollections.observableArrayList(vehicleTypes));
        typeComboBox.setPromptText("Select Type");

        TextField modelField = new TextField();
        modelField.setPromptText("Model");

        TextField rateField = new TextField();
        rateField.setPromptText("Rate per Km");

        CheckBox availabilityCheckBox = new CheckBox("Available");
        TextField imagePathField = new TextField();
        imagePathField.setPromptText("Image Path");
        Button selectImageBtn = new Button("Select Image");

        selectImageBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imagePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        Button addVehicleBtn = new Button("➕ Add Vehicle");
        Button updateVehicleBtn = new Button("✏️ Update Vehicle");
        Button deleteVehicleBtn = new Button("❌ Delete Vehicle");
        Button backButton = new Button("⬅ Back");

        addVehicleBtn.setOnAction(e -> addVehicle(modelField.getText(), plateField.getText(), typeComboBox.getValue(), Double.parseDouble(rateField.getText()), availabilityCheckBox.isSelected(), imagePathField.getText()));
        updateVehicleBtn.setOnAction(e -> updateVehicleAvailability());
        deleteVehicleBtn.setOnAction(e -> deleteVehicle());
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(new ManagerDashboard().getRoot(), 800, 600)));

        HBox inputBox = new HBox(10, plateField, typeComboBox, modelField, rateField, availabilityCheckBox, imagePathField, selectImageBtn, addVehicleBtn);
        inputBox.setPadding(new Insets(10));

        HBox buttonBox = new HBox(10, updateVehicleBtn, deleteVehicleBtn, backButton);
        buttonBox.setPadding(new Insets(10));

        VBox layout = new VBox(15, title, vehicleTable, inputBox, buttonBox);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addVehicle(String model, String plateNumber, String type, double ratePerKm, boolean availability, String imagePath) {
        if (model.isEmpty() || plateNumber.isEmpty() || type == null) {
            showAlert("Error", "All fields are required!");
            return;
        }
        vehicleController.addVehicle(model, plateNumber, type, ratePerKm, availability, imagePath);
        refreshTable();
    }

    private void refreshTable() {
        Platform.runLater(() -> vehicleList.setAll(vehicleController.getAllVehicles()));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

