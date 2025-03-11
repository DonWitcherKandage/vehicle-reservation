package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerDashboard extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label welcomeLabel = new Label("Welcome to MegaCity Cab!");
        Button bookRideBtn = new Button("Book a Ride");
        Button viewBookingsBtn = new Button("View My Bookings");
        Button ongoingTripsBtn = new Button("Ongoing Trips");
        Button profileBtn = new Button("View Profile");
        Button logoutBtn = new Button("Logout");

        bookRideBtn.setOnAction(e -> {
            BookingUI bookingUI = new BookingUI();
            bookingUI.start(new Stage());
        });

        VBox layout = new VBox(15, welcomeLabel, bookRideBtn, viewBookingsBtn, ongoingTripsBtn, profileBtn, logoutBtn);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}