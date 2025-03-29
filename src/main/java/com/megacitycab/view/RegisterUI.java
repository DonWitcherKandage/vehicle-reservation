package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Registration UI for new users (customers or managers).
 */
public class RegisterUI extends Application {
    private final AuthController authController = new AuthController();
    private final String role;

    public RegisterUI(String role) {
        this.role = role;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Register as " + role);

        Label titleLabel = new Label("MegaCity Cab - Register as " + role);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        TextField addressField = new TextField();
        addressField.setPromptText("Enter Address");

        TextField nicField = new TextField();
        nicField.setPromptText("Enter NIC");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter Phone Number");

        Button registerBtn = new Button("Register");
        Button backBtn = new Button("Back");

        styleButton(registerBtn);
        styleButton(backBtn);

        // ✅ Handle Registration
        registerBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String address = addressField.getText();
            String nic = nicField.getText();
            String phone = phoneField.getText();

            if (username.isEmpty() || password.isEmpty() || address.isEmpty() || nic.isEmpty() || phone.isEmpty()) {
                showErrorMessage("All fields must be filled out!");
            } else {
                boolean success = role.equals("CUSTOMER")
                        ? authController.registerCustomer(username, password, address, nic, phone)
                        : authController.registerManager(username, password, address, nic, phone);
                if (success) {
                    showSuccessMessage("Registration Successful!");
                    primaryStage.close();
                    new LoginUI(role).start(new Stage()); // ✅ Redirect to login with role
                } else {
                    showErrorMessage("Registration failed. Try again.");
                }
            }
        });

        // ✅ Handle Back Button (Return to User Selection)
        backBtn.setOnAction(e -> {
            new UserSelectionUI().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, titleLabel, usernameField, passwordField, addressField, nicField, phoneField, registerBtn, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Styles buttons for consistency.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px;");
    }

    /**
     * Shows a success message popup.
     */
    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows an error message popup.
     */
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}