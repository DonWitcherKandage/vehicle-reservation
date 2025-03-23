package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.controller.ManagerController;
import com.megacitycab.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends Application {
    private final AuthController authController = new AuthController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);

        // UI Components
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        userLabel.setStyle("-fx-font-weight: bold;");
        
        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passLabel.setStyle("-fx-font-weight: bold;");
        
        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");
        Label statusLabel = new Label();

        // Button Styling
        loginBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");
        backBtn.setStyle("-fx-background-color: #696969; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");

        // Login Handler
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            // Validation
            if(username.isEmpty()) {
                showError("Username Error", "Username cannot be empty!");
                return;
            }
            if(password.isEmpty()) {
                showError("Password Error", "Password cannot be empty!");
                return;
            }

            User loggedInUser = authController.login(username, password);

            if (loggedInUser != null) {
                handleSuccessfulLogin(primaryStage, loggedInUser);
            } else {
                statusLabel.setText("❌ Invalid credentials! Check username/password");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Back Button Handler
        backBtn.setOnAction(e -> {
            primaryStage.close();
            new UserSelectionUI().start(new Stage());
        });

        // Layout
        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, 
                              loginBtn, backBtn, statusLabel);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F5F5F5;");

        primaryStage.setScene(new Scene(layout, 400, 350));
        primaryStage.show();
    }

    private void handleSuccessfulLogin(Stage loginStage, User user) {
        loginStage.close();
        
        try {
            Stage dashboardStage = new Stage();
            if (user.getRole().equalsIgnoreCase("MANAGER")) {
                ManagerController controller = new ManagerController(dashboardStage);
                new ManagerDashboard(controller).start(dashboardStage);
            } else if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
                new CustomerDashboard().start(new Stage());
            } else {
                showError("Role Error", "Unknown user role: " + user.getRole());
            }
        } catch (Exception e) {
            showError("Navigation Error", "Failed to open dashboard: " + e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}