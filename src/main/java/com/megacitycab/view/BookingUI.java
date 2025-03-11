package com.megacitycab.view;

import com.megacitycab.controller.BookingController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BookingUI extends Application {

    private BookingController bookingController = new BookingController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book a Ride");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label destinationLabel = new Label("Destination:");
        TextField destinationField = new TextField();
        
        Label vehicleLabel = new Label("Vehicle Type:");
        ComboBox<String> vehicleBox = new ComboBox<>();
        vehicleBox.getItems().addAll("Car", "Van", "SUV");

        Label fareLabel = new Label("Total Fare:");
        TextField fareField = new TextField();
        fareField.setEditable(false);

        Button bookButton = new Button("Book Now");
        bookButton.setOnAction(e -> {
            bookingController.createBooking("cust123", "car567", "driver789", destinationField.getText(), 100.0);
        });

        grid.add(destinationLabel, 0, 0);
        grid.add(destinationField, 1, 0);
        grid.add(vehicleLabel, 0, 1);
        grid.add(vehicleBox, 1, 1);
        grid.add(fareLabel, 0, 2);
        grid.add(fareField, 1, 2);
        grid.add(bookButton, 1, 3);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}