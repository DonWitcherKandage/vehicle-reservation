package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.model.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends Application {
    private AuthController authController = new AuthController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            User user = authController.login(usernameField.getText(), passwordField.getText());
            if (user != null) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");

                // Redirect users to appropriate dashboard
                if (user.getRole().equals("CUSTOMER")) {
                    CustomerDashboard customerDashboard = new CustomerDashboard();
                    customerDashboard.start(new Stage());
                } else {
                    ManagerDashboard managerDashboard = new ManagerDashboard();
                    managerDashboard.start(new Stage());
                }
                primaryStage.close();  // Close login window
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid Credentials!");
            }
        });

        VBox layout = new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField, loginButton);
        Scene scene = new Scene(layout, 300, 250);
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