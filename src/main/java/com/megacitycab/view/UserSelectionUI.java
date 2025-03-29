package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Allows users to select whether they are a customer or manager before logging in.
 */
public class UserSelectionUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Select User Type");

        Button customerBtn = new Button("Customer Login");
        Button managerBtn = new Button("Manager Login");
        Button registerCustomerBtn = new Button("Register as Customer");
        Button registerManagerBtn = new Button("Register as Manager");
        Button exitBtn = new Button("Exit");

        // ✅ Style buttons
        styleButton(customerBtn);
        styleButton(managerBtn);
        styleButton(registerCustomerBtn);
        styleButton(registerManagerBtn);
        styleExitButton(exitBtn);

        // ✅ Button Actions
        customerBtn.setOnAction(e -> {
            new LoginUI("CUSTOMER").start(new Stage());
            primaryStage.close();
        });

        managerBtn.setOnAction(e -> {
            new LoginUI("MANAGER").start(new Stage());
            primaryStage.close();
        });

        registerCustomerBtn.setOnAction(e -> {
            new RegisterUI("CUSTOMER").start(new Stage());
            primaryStage.close();
        });

        registerManagerBtn.setOnAction(e -> {
            new RegisterUI("MANAGER").start(new Stage());
            primaryStage.close();
        });

        exitBtn.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(15, customerBtn, managerBtn, registerCustomerBtn, registerManagerBtn, exitBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 450, 350); // Make dialog a little bigger
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Styles buttons for consistency.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 180px; -fx-padding: 8px;");
    }

    /**
     * Styles the exit button with red color.
     */
    private void styleExitButton(Button button) {
        button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 180px; -fx-padding: 8px;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}