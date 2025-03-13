package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ForgotPasswordUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Forgot Password");

        Label emailLabel = new Label("Enter your email:");
        TextField emailField = new TextField();
        
        Button resetButton = new Button("Reset Password");
        Label statusLabel = new Label();

        resetButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                statusLabel.setText("⚠️ Please enter your email.");
            } else {
                statusLabel.setText("✅ Password reset instructions sent to: " + email);
            }
        });

        VBox layout = new VBox(10, emailLabel, emailField, resetButton, statusLabel);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}