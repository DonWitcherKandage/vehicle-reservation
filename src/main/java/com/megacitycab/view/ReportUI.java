package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Generate Reports");

        Label titleLabel = new Label("Generate Reports");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ComboBox<String> reportTypeBox = new ComboBox<>();
        reportTypeBox.getItems().addAll("Booking Summary", "Driver Performance", "Revenue Report");

        Button generateButton = new Button("Generate Report");

        TextArea reportArea = new TextArea();
        reportArea.setEditable(false);

        generateButton.setOnAction(e -> {
            String reportType = reportTypeBox.getValue();
            if (reportType == null || reportType.isEmpty()) {
                reportArea.setText("⚠️ Please select a report type.");
                return;
            }
            reportArea.setText("📊 Generating " + reportType + "...\n\n[Report Data Placeholder]");
        });

        VBox layout = new VBox(10, titleLabel, reportTypeBox, generateButton, reportArea);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}