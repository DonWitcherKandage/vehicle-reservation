package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterUI extends Application {
    private AuthController authController = new AuthController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Registration");

        // UI Components
        Label titleLabel = new Label("Register as a Customer");
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Label nicLabel = new Label("NIC:");
        TextField nicField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Button registerButton = new Button("Register");

        // Handle Registration
        registerButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String address = addressField.getText().trim();
            String nic = nicField.getText().trim();
            String phone = phoneField.getText().trim();

            if (username.isEmpty() || password.isEmpty() || address.isEmpty() || nic.isEmpty() || phone.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
                return;
            }

            authController.registerCustomer(username, password, address, nic, phone);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Registration Successful!");
        });

        // Layout Setup
        VBox layout = new VBox(10, titleLabel, usernameLabel, usernameField, passwordLabel, passwordField,
                addressLabel, addressField, nicLabel, nicField, phoneLabel, phoneField, registerButton);
        layout.setPadding(new Insets(20));

        // Scene Setup
        Scene scene = new Scene(layout, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}