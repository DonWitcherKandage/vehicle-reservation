package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OngoingTripsUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ongoing Trips");

        Label titleLabel = new Label("Your Ongoing Trips");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ListView<String> tripsList = new ListView<>();
        tripsList.getItems().addAll(
                "Trip #123 - Destination: City Mall - Driver: John Doe",
                "Trip #124 - Destination: Airport - Driver: Alex Smith"
        );

        Button refreshButton = new Button("Refresh");

        refreshButton.setOnAction(e -> {
            tripsList.getItems().clear();
            tripsList.getItems().addAll(
                "Trip #125 - Destination: Central Park - Driver: Emma Johnson"
            );
        });

        VBox layout = new VBox(10, titleLabel, tripsList, refreshButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}