package com.megacitycab.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportUI {
    private final Stage previousStage;

    public ReportUI(Stage previousStage) {
        this.previousStage = previousStage;
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Report Buttons
        Button bookingReportBtn = new Button("Generate Booking Report");
        Button vehicleReportBtn = new Button("Generate Vehicle Report");
        Button driverReportBtn = new Button("Generate Driver Report");
        Button backBtn = new Button("⬅ Back");

        // Styling
        bookingReportBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        vehicleReportBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        driverReportBtn.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: #666; -fx-text-fill: white;");

        // Button Actions
        backBtn.setOnAction(e -> {
            primaryStage.close();
            previousStage.show();
        });

        root.getChildren().addAll(bookingReportBtn, vehicleReportBtn, driverReportBtn, backBtn);

        // Scene Setup
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Generate Reports");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}