package com.megacitycab.view;

import com.megacitycab.controller.DriverController;
import com.megacitycab.controller.ManagerController;
import com.megacitycab.model.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageDriversUI {
    private final Stage previousStage;
    private final DriverController driverController = new DriverController();
    private final ObservableList<Driver> drivers = FXCollections.observableArrayList();
    private TableView<Driver> driverTable;

    public ManageDriversUI(Stage previousStage) {
        this.previousStage = previousStage;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Drivers");

        // Back button
        Button backBtn = new Button("⬅ Back");
        backBtn.setStyle("-fx-background-color: #666; -fx-text-fill: white;");
        // Update the back button action handler in ManageDriversUI
        backBtn.setOnAction(e -> {
            primaryStage.close();
            if (previousStage != null) {
                // Create and show a new ManagerDashboard with a new ManagerController
                Stage newManagerStage = new Stage();
                new ManagerDashboard(new ManagerController(newManagerStage)).start(newManagerStage);
            }
        });


        // Table for displaying drivers
        driverTable = new TableView<>();
        driverTable.setItems(drivers);

        TableColumn<Driver, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().driverIdProperty());

        TableColumn<Driver, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Driver, Boolean> availabilityColumn = new TableColumn<>("Available");
        availabilityColumn.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty().asObject());

        driverTable.getColumns().addAll(idColumn, nameColumn, availabilityColumn);

        // Form for adding and updating drivers
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        TextField idField = new TextField();
        idField.setPromptText("Driver ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        CheckBox availabilityCheckBox = new CheckBox("Available");

        Button addBtn = new Button("Add Driver");
        addBtn.setOnAction(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            boolean availability = availabilityCheckBox.isSelected();
            if (driverController.addDriver(id, name, availability)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Driver added successfully!");
                refreshDrivers();
                clearForm(idField, nameField, availabilityCheckBox);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add driver.");
            }
        });

        Button updateBtn = new Button("Update Driver");
        updateBtn.setOnAction(e -> {
            Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
            if (selectedDriver == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Select driver first.");
                return;
            }

            String id = idField.getText();
            String name = nameField.getText();
            boolean availability = availabilityCheckBox.isSelected();
            if (driverController.updateDriver(id, name, availability)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Driver updated successfully!");
                refreshDrivers();
                clearForm(idField, nameField, availabilityCheckBox);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update driver.");
            }
        });

        Button deleteBtn = new Button("Delete Driver");
        deleteBtn.setOnAction(e -> {
            Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
            String id = idField.getText();

            if (selectedDriver == null && id.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Select a driver or enter Driver ID to delete.");
                return;
            }

            if (selectedDriver != null) {
                id = selectedDriver.getDriverId();
            }

            if (driverController.deleteDriver(id)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Driver deleted successfully!");
                refreshDrivers();
                clearForm(idField, nameField, availabilityCheckBox);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete driver.");
            }
        });

        form.add(new Label("Driver ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Availability:"), 0, 2);
        form.add(availabilityCheckBox, 1, 2);
        form.add(addBtn, 0, 3);
        form.add(updateBtn, 1, 3);
        form.add(deleteBtn, 2, 3);

        VBox layout = new VBox(10, backBtn, driverTable, form);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();

        refreshDrivers();
    }

    private void refreshDrivers() {
        drivers.setAll(driverController.getAllDrivers());
    }

    private void clearForm(TextField idField, TextField nameField, CheckBox availabilityCheckBox) {
        idField.clear();
        nameField.clear();
        availabilityCheckBox.setSelected(false);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}