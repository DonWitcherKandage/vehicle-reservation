package com.megacitycab.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Dashboard");

        Label welcomeLabel = new Label("Welcome to MegaCity Cab!");

        Button bookRideBtn = new Button("Book a Ride");
        Button viewBookingsBtn = new Button("View My Bookings");
        Button ongoingTripsBtn = new Button("Ongoing Trips");
        Button profileBtn = new Button("View Profile");
        Button logoutBtn = new Button("Logout");

        // Handle Button Clicks
        bookRideBtn.setOnAction(e -> {
            BookingUI bookingUI = new BookingUI();
            bookingUI.start(new Stage());
        });

        viewBookingsBtn.setOnAction(e -> {
            ViewBookingsUI viewBookingsUI = new ViewBookingsUI();
            viewBookingsUI.start(new Stage());
        });

        ongoingTripsBtn.setOnAction(e -> {
            OngoingTripsUI ongoingTripsUI = new OngoingTripsUI();
            ongoingTripsUI.start(new Stage());
        });

        profileBtn.setOnAction(e -> {
            ProfileUI profileUI = new ProfileUI();
            profileUI.start(new Stage());
        });

        logoutBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> {
            LoginUI loginUI = new LoginUI();
            loginUI.start(new Stage());
            primaryStage.close();  // Close Customer Dashboard
        });

        VBox layout = new VBox(15, welcomeLabel, bookRideBtn, viewBookingsBtn, ongoingTripsBtn, profileBtn, logoutBtn);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}