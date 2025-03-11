package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.megacitycab.controller.ManagerController;

public class ManageDriversUI extends Application {
    private ManagerController managerController = new ManagerController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Drivers");

        Label nameLabel = new Label("Driver Name:");
        TextField nameField = new TextField();

        CheckBox availableCheckBox = new CheckBox("Available");

        Button addButton = new Button("Add Driver");
        addButton.setOnAction(e -> {
            managerController.addDriver(nameField.getText(), availableCheckBox.isSelected());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Driver Added Successfully!");
            alert.show();
        });

        VBox layout = new VBox(10, nameLabel, nameField, availableCheckBox, addButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}