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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successful!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Credentials!");
                alert.show();
            }
        });

        VBox layout = new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField, loginButton);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}