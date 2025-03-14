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
        Button exitBtn = new Button("Exit");

        // ✅ Style buttons
        styleButton(customerBtn);
        styleButton(managerBtn);
        styleExitButton(exitBtn);

        // ✅ Button Actions
        customerBtn.setOnAction(e -> {
            new LoginUI().start(new Stage());
            primaryStage.close();
        });

        managerBtn.setOnAction(e -> {
            new LoginUI().start(new Stage());
            primaryStage.close();
        });

        exitBtn.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(20, customerBtn, managerBtn, exitBtn);
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
     * Styles the exit button with red color.
     */
    private void styleExitButton(Button button) {
        button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}