package com.megacitycab.view;

import com.megacitycab.controller.AuthController;
import com.megacitycab.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Login UI for users.
 */
public class LoginUI extends Application {
    private final AuthController authController = new AuthController();
    private String userType;

    public LoginUI() { }  // ✅ Added default constructor

    public LoginUI(String userType) {
        this.userType = userType;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login - " + userType);

        Label titleLabel = new Label("MegaCity Cab - " + userType + " Login");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");

        styleButton(loginBtn);
        styleButton(backBtn);

        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showErrorMessage("Please enter both username and password!");
            } else {
                User user = authController.login(username, password);

                if (user != null) {
                    showSuccessMessage("Login Successful!");

                    primaryStage.close();
                    if (userType.equals("Customer")) {
                        new CustomerDashboard().start(new Stage());
                    } else {
                        new ManagerDashboard().start(new Stage());
                    }
                } else {
                    showErrorMessage("Invalid login! Try again.");
                }
            }
        });

        backBtn.setOnAction(e -> {
            new UserSelectionUI().start(new Stage());
            primaryStage.close();
        });

        VBox layout = new VBox(15, titleLabel, usernameField, passwordField, loginBtn, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px;");
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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