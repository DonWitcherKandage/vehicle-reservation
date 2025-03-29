package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.controller.ManagerController;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Manager;
import com.megacitycab.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles user login.
 */
public class LoginUI extends Application {
    private final AuthController authController = new AuthController();
    private final String role;

    public LoginUI(String role) {
        this.role = role;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(role + " Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");

        styleButton(loginBtn);
        styleButton(backBtn);

        // ✅ Handle Login
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = authController.login(username, password, role);
            if (user != null) {
                showSuccessMessage("Login Successful!");
                primaryStage.close();
                // Redirect to dashboard based on role
                if (role.equalsIgnoreCase("CUSTOMER")) {
                    new CustomerDashboard((Customer) user).start(new Stage());
                } else if (role.equalsIgnoreCase("MANAGER")) {
                    ManagerController managerController = new ManagerController(new Stage());
                    new ManagerDashboard(managerController).start(new Stage());
                }
            } else {
                showErrorMessage("Invalid username or password. Try again.");
            }
        });

        // ✅ Handle Back Button (Return to User Selection)
        backBtn.setOnAction(e -> {
            new UserSelectionUI().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, usernameField, passwordField, loginBtn, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
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