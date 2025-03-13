package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBookingsUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Bookings");

        Label titleLabel = new Label("Your Bookings");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ListView<String> bookingsList = new ListView<>();
        bookingsList.getItems().addAll(
                "Booking #101 - Status: Completed",
                "Booking #102 - Status: Cancelled",
                "Booking #103 - Status: Pending"
        );

        Button refreshButton = new Button("Refresh");

        refreshButton.setOnAction(e -> {
            bookingsList.getItems().clear();
            bookingsList.getItems().addAll(
                "Booking #104 - Status: Accepted",
                "Booking #105 - Status: Completed"
            );
        });

        VBox layout = new VBox(10, titleLabel, bookingsList, refreshButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}