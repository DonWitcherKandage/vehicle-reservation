package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.megacitycab.model.Driver;

public class ManageDriversUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Table to display drivers
        TableView<Driver> driverTable = new TableView<>();

        // Buttons
        Button addDriverButton = new Button("Add Driver");
        Button editDriverButton = new Button("Edit Driver");
        Button deleteDriverButton = new Button("Delete Driver");

        // Add components to layout
        root.getChildren().addAll(driverTable, addDriverButton, editDriverButton, deleteDriverButton);

        // Event Handlers
        addDriverButton.setOnAction(e -> {
            // Open Add Driver Dialog
        });

        editDriverButton.setOnAction(e -> {
            // Open Edit Driver Dialog
        });

        deleteDriverButton.setOnAction(e -> {
            // Delete Selected Driver
        });

        // Set Scene
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Manage Drivers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}