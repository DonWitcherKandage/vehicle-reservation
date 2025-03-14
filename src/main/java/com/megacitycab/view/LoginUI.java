package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends Application {
    private AuthController authController = new AuthController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");

        Label statusLabel = new Label();

        // 🎯 Style the buttons for better UI
        loginBtn.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");
        backBtn.setStyle("-fx-background-color: grey; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");

        // 🔥 Handle Login Button Click
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User loggedInUser = authController.login(username, password);

            if (loggedInUser != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, " + loggedInUser.getUsername() + "!");
                alert.showAndWait();

                primaryStage.close(); // Close login screen
                
                // 🔥 Navigate to the correct dashboard based on role
                if (loggedInUser.getRole().equalsIgnoreCase("CUSTOMER")) {
                    new CustomerDashboard().start(new Stage());
                } else if (loggedInUser.getRole().equalsIgnoreCase("MANAGER")) {
                    new ManagerDashboard().start(new Stage());
                }
            } else {
                statusLabel.setText("❌ Invalid login! Try again.");
            }
        });

        // 🔙 Handle Back Button Click
        backBtn.setOnAction(e -> {
            new UserSelectionUI().start(new Stage());  // Go back to user selection screen
            primaryStage.close();
        });

        VBox layout = new VBox(10, userLabel, usernameField, passLabel, passwordField, loginBtn, backBtn, statusLabel);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}