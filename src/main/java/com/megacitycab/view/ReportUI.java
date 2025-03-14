package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Buttons
        Button bookingReportButton = new Button("Generate Booking Report");
        Button vehicleReportButton = new Button("Generate Vehicle Report");
        Button driverReportButton = new Button("Generate Driver Report");

        // Add components to layout
        root.getChildren().addAll(bookingReportButton, vehicleReportButton, driverReportButton);

        // Event Handlers
        bookingReportButton.setOnAction(e -> {
            // Generate Booking Report
        });

        vehicleReportButton.setOnAction(e -> {
            // Generate Vehicle Report
        });

        driverReportButton.setOnAction(e -> {
            // Generate Driver Report
        });

        // Set Scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Generate Reports");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}