package com.megacitycab.view;

import com.megacitycab.controller.BookingController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingUI extends Application {
    private BookingController bookingController;

    @Override
    public void start(Stage primaryStage) {
        bookingController = new BookingController();

        primaryStage.setTitle("Book a Ride");

        Label destinationLabel = new Label("Destination:");
        TextField destinationField = new TextField();

        Label dateLabel = new Label("Date:");
        TextField dateField = new TextField();

        Label timeLabel = new Label("Time:");
        TextField timeField = new TextField();

        Label vehicleTypeLabel = new Label("Vehicle Type:");
        ComboBox<String> vehicleTypeBox = new ComboBox<>();
        vehicleTypeBox.getItems().addAll("Car", "Van", "SUV");

        Label priceLabel = new Label("Price (calculated):");
        Label priceValue = new Label("Rs. 0.00");

        Button calculatePriceBtn = new Button("Calculate Price");
        calculatePriceBtn.setOnAction(e -> {
            double ratePerKm = 50.0; // Example rate
            double distance = 10; // Example distance
            double price = ratePerKm * distance;
            priceValue.setText("Rs. " + price);
        });

        Button bookNowBtn = new Button("Book Now");
        bookNowBtn.setOnAction(e -> {
            String destination = destinationField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            String vehicleType = vehicleTypeBox.getValue();
            double price = Double.parseDouble(priceValue.getText().replace("Rs. ", ""));

            bookingController.addBooking(destination, date, time, vehicleType, price, 1); // Example Customer ID

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Confirmed");
            alert.setHeaderText(null);
            alert.setContentText("Your ride has been booked successfully!");
            alert.showAndWait();

            primaryStage.close();
        });

        VBox layout = new VBox(10, destinationLabel, destinationField, dateLabel, dateField, timeLabel, timeField,
                vehicleTypeLabel, vehicleTypeBox, priceLabel, priceValue, calculatePriceBtn, bookNowBtn);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}