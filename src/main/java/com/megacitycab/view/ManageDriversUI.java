package com.megacitycab.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageDriversUI {
    private final Stage previousStage;

    // ✅ Correct constructor with Stage parameter
    public ManageDriversUI(Stage previousStage) {
        this.previousStage = previousStage;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manage Drivers");
        
        Button backBtn = new Button("⬅ Back");
        backBtn.setStyle("-fx-background-color: #666; -fx-text-fill: white;");
        backBtn.setOnAction(e -> {
            primaryStage.close();
            previousStage.show(); // Return to manager dashboard
        });

        VBox layout = new VBox(10, backBtn);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }
}