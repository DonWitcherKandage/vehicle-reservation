package com.megacitycab.view;

import com.megacitycab.controller.BookingController;
import com.megacitycab.model.Booking;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ViewBookingsUI extends Application {
    private BookingController bookingController;
    private int customerId;

    public ViewBookingsUI(int customerId) {
        this.customerId = customerId;
        this.bookingController = new BookingController();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Bookings");

        Label titleLabel = new Label("Your Bookings");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ListView<String> bookingsList = new ListView<>();

        // Load bookings for the customer
        loadBookings(bookingsList);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> loadBookings(bookingsList));

        VBox layout = new VBox(10, titleLabel, bookingsList, refreshButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadBookings(ListView<String> bookingsList) {
        List<Booking> bookings = bookingController.getBookingsForCustomer(customerId);
        bookingsList.getItems().clear();
        for (Booking booking : bookings) {
            bookingsList.getItems().add("Booking #" + booking.getBookingId() + " - Status: " + booking.getStatus() + " - Destination: " + booking.getDestination());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}