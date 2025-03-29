package com.megacitycab.view;

import com.megacitycab.controller.VehicleController;
import com.megacitycab.model.Vehicle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ManageVehiclesUI {
    private final VehicleController vehicleController;
    private final ObservableList<Vehicle> vehicleList;
    private final TableView<Vehicle> vehicleTable = new TableView<>();
    private final Stage managerDashboardStage;

    private TextField modelField, plateField, rateField, imagePathField;
    private ComboBox<String> typeComboBox;
    private CheckBox availabilityCheckBox;

    public ManageVehiclesUI(VehicleController vehicleController, Stage managerDashboardStage) {
        this.vehicleController = vehicleController;
        this.managerDashboardStage = managerDashboardStage;
        this.vehicleList = FXCollections.observableArrayList(vehicleController.getAllVehicles());
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Vehicles");
        Label title = new Label("Manage Vehicles");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        setupTable();
        initializeInputFields();

        Button selectImageBtn = new Button("Select Image");
        selectImageBtn.setOnAction(e -> handleImageSelection(primaryStage));

        Button addVehicleBtn = new Button("➕ Add Vehicle");
        Button updateVehicleBtn = new Button("✏️ Update Vehicle");
        Button deleteVehicleBtn = new Button("❌ Delete Vehicle");
        Button backButton = new Button("⬅ Back");

        setupButtonActions(addVehicleBtn, updateVehicleBtn, deleteVehicleBtn, backButton, primaryStage);

        HBox inputBox = new HBox(10,
                modelField, plateField, typeComboBox, rateField,
                availabilityCheckBox, imagePathField, selectImageBtn, addVehicleBtn
        );
        inputBox.setPadding(new Insets(10));

        HBox buttonBox = new HBox(10, updateVehicleBtn, deleteVehicleBtn, backButton);
        buttonBox.setPadding(new Insets(10));

        VBox layout = new VBox(15, title, vehicleTable, inputBox, buttonBox);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 1200, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupTable() {
        setupTableColumns();
        vehicleTable.setItems(vehicleList);
        vehicleTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        vehicleTable.setRowFactory(tv -> {
            TableRow<Vehicle> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    showVehicleDetailWindow(row.getItem());
                }
            });
            return row;
        });
    }

    private void setupTableColumns() {
        TableColumn<Vehicle, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vehicle, String> plateCol = new TableColumn<>("Plate");
        plateCol.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));

        TableColumn<Vehicle, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Vehicle, Double> rateCol = new TableColumn<>("Rate/km");
        rateCol.setCellValueFactory(new PropertyValueFactory<>("ratePerKm"));

        TableColumn<Vehicle, Boolean> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));

        TableColumn<Vehicle, String> imageCol = new TableColumn<>("Image");
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imagePath"));

        vehicleTable.getColumns().addAll(
                idCol, modelCol, plateCol, typeCol, rateCol, availableCol, imageCol
        );
    }

    private void initializeInputFields() {
        plateField = new TextField();
        plateField.setPromptText("Plate Number (ABC-1234)");
        plateField.setPrefWidth(150);

        typeComboBox = new ComboBox<>();
        List<String> vehicleTypes = Arrays.asList("Sedan", "SUV", "Van", "Luxury");
        typeComboBox.setItems(FXCollections.observableArrayList(vehicleTypes));
        typeComboBox.setPromptText("Select Type");
        typeComboBox.setPrefWidth(120);

        modelField = new TextField();
        modelField.setPromptText("Model");
        modelField.setPrefWidth(150);

        rateField = new TextField();
        rateField.setPromptText("Rate per Km");
        rateField.setPrefWidth(120);

        availabilityCheckBox = new CheckBox("Available");
        imagePathField = new TextField();
        imagePathField.setPromptText("Image Path");
        imagePathField.setPrefWidth(200);
    }

    private void setupButtonActions(Button add, Button update, Button delete, Button back, Stage primaryStage) {
        add.setOnAction(e -> handleAddVehicle());
        update.setOnAction(e -> handleUpdateVehicle());
        delete.setOnAction(e -> handleDeleteVehicle());
        back.setOnAction(e -> {
            primaryStage.close();
            managerDashboardStage.show();
        });
    }

    private void handleAddVehicle() {
        try {
            Vehicle vehicle = new Vehicle(
                    UUID.randomUUID().toString(),
                    modelField.getText(),
                    plateField.getText(),
                    typeComboBox.getValue(),
                    Double.parseDouble(rateField.getText()),
                    availabilityCheckBox.isSelected(),
                    imagePathField.getText()
            );

            vehicleController.addVehicle(vehicle);
            clearFields();
            refreshTable();
            showAlert("Success", "Vehicle added successfully!");
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for rate");
        } catch (Exception e) {
            showAlert("Error", "Failed to add vehicle: " + e.getMessage());
        }
    }

    private void handleUpdateVehicle() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a vehicle first!");
            return;
        }

        try {
            Vehicle updatedVehicle = new Vehicle(
                    selected.getId(),
                    modelField.getText(),
                    plateField.getText(),
                    typeComboBox.getValue(),
                    Double.parseDouble(rateField.getText()),
                    availabilityCheckBox.isSelected(),
                    imagePathField.getText()
            );

            vehicleController.updateVehicle(updatedVehicle);
            refreshTable();
            showAlert("Success", "Vehicle updated successfully!");
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for rate");
        }
    }

    private void handleDeleteVehicle() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a vehicle to delete!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete Vehicle: " + selected.getPlateNumber());
        confirm.setContentText("Are you sure you want to permanently delete this vehicle?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            vehicleController.deleteVehicle(selected.getId());
            refreshTable();
            showAlert("Success", "Vehicle deleted successfully!");
        }
    }

    private void showVehicleDetailWindow(Vehicle vehicle) {
        Stage detailStage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Image display
        ImageView imageView = new ImageView();
        try {
            File imageFile = new File(vehicle.getImagePath());
            if (imageFile.exists()) {
                imageView.setImage(new Image(imageFile.toURI().toString()));
            } else {
                throw new Exception("Image not found");
            }
        } catch (Exception e) {
            imageView.setImage(new Image(getClass().getResourceAsStream("/placeholder.png")));
        }
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        // Form fields
        TextField modelField = new TextField(vehicle.getModel());
        TextField plateField = new TextField(vehicle.getPlateNumber());
        ComboBox<String> typeCombo = new ComboBox<>(FXCollections.observableArrayList("Sedan", "SUV", "Van", "Luxury"));
        typeCombo.getSelectionModel().select(vehicle.getType());
        TextField rateField = new TextField(String.valueOf(vehicle.getRatePerKm()));
        CheckBox availableCheck = new CheckBox("Available");
        availableCheck.setSelected(vehicle.isAvailable());

        // Update button
        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            try {
                Vehicle updatedVehicle = new Vehicle(
                        vehicle.getId(),
                        modelField.getText(),
                        plateField.getText(),
                        typeCombo.getValue(),
                        Double.parseDouble(rateField.getText()),
                        availableCheck.isSelected(),
                        vehicle.getImagePath()
                );

                vehicleController.updateVehicle(updatedVehicle);
                refreshTable();
                detailStage.close();
                showAlert("Success", "Vehicle updated successfully!");
            } catch (NumberFormatException ex) {
                showAlert("Error", "Invalid rate value");
            }
        });

        // Delete button
        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            vehicleController.deleteVehicle(vehicle.getId());
            refreshTable();
            detailStage.close();
            showAlert("Success", "Vehicle deleted successfully!");
        });

        layout.getChildren().addAll(
                imageView,
                new Label("Model:"), modelField,
                new Label("Plate:"), plateField,
                new Label("Type:"), typeCombo,
                new Label("Rate/km:"), rateField,
                availableCheck,
                new HBox(10, updateBtn, deleteBtn)
        );

        detailStage.setScene(new Scene(layout, 400, 600));
        detailStage.setTitle("Vehicle Details - " + vehicle.getPlateNumber());
        detailStage.show();
    }

    private void handleImageSelection(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            imagePathField.setText(file.getAbsolutePath());
        }
    }

    private void refreshTable() {
        Platform.runLater(() -> {
            vehicleList.setAll(vehicleController.getAllVehicles());
            vehicleTable.refresh();
        });
    }

    private void clearFields() {
        modelField.clear();
        plateField.clear();
        typeComboBox.getSelectionModel().clearSelection();
        rateField.clear();
        availabilityCheckBox.setSelected(false);
        imagePathField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}