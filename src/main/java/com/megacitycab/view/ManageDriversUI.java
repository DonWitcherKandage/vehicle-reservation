package com.megacitycab.view;

import com.megacitycab.controller.DriverController;
import com.megacitycab.model.Driver;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ManageDriversUI extends Application {
    private DriverController driverController = new DriverController();
    private ObservableList<Driver> driverList;
    private TableView<Driver> driverTable;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Drivers");

        Label titleLabel = new Label("Driver Management");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Table to Display Drivers
        driverTable = new TableView<>();
        TableColumn<Driver, String> idColumn = new TableColumn<>("Driver ID");
        idColumn.setCellValueFactory(data -> data.getValue().driverIdProperty());

        TableColumn<Driver, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Driver, Boolean> availabilityColumn = new TableColumn<>("Available");
        availabilityColumn.setCellValueFactory(data -> data.getValue().availabilityProperty());

        driverTable.getColumns().addAll(idColumn, nameColumn, availabilityColumn);
        loadDrivers();

        Label nameLabel = new Label("Driver Name:");
        TextField nameField = new TextField();

        CheckBox availableCheckBox = new CheckBox("Available");

        Button addButton = new Button("Add Driver");
        Button deleteButton = new Button("Delete Driver");

        addButton.setOnAction(e -> {
            if (!nameField.getText().isEmpty()) {
                driverController.addDriver(nameField.getText(), availableCheckBox.isSelected());
                loadDrivers();
            }
        });

        deleteButton.setOnAction(e -> {
            Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
            if (selectedDriver != null) {
                driverController.deleteDriver(selectedDriver.getDriverId());
                loadDrivers();
            }
        });

        HBox formLayout = new HBox(10, nameLabel, nameField, availableCheckBox);
        VBox layout = new VBox(10, titleLabel, driverTable, formLayout, addButton, deleteButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadDrivers() {
        List<Driver> drivers = driverController.getAllDrivers();
        driverList = FXCollections.observableArrayList(drivers);
        driverTable.setItems(driverList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}