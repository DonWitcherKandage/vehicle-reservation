package com.megacitycab.view;

//import com.megacitycab.controller.BookingController;
import com.megacitycab.util.BookingNotifier;
import com.megacitycab.util.CustomerObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerDashboard extends Application {
    private ObservableList<String> notificationList = FXCollections.observableArrayList();
    private BookingNotifier notifier;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Dashboard");

        Label welcomeLabel = new Label("Welcome to MegaCity Cab!");

        Button bookRideBtn = new Button("🚖 Book a Ride");
        Button viewBookingsBtn = new Button("📜 View My Bookings");
        Button ongoingTripsBtn = new Button("🛣️ Ongoing Trips");
        Button profileBtn = new Button("👤 View Profile");
        Button logoutBtn = new Button("🚪 Logout");

        // Style Buttons
        bookRideBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        viewBookingsBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        ongoingTripsBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        profileBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        logoutBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");

        // Notifications Section
        Label notificationLabel = new Label("🔔 Notifications:");
        ListView<String> notificationView = new ListView<>(notificationList);
        notificationView.setPrefHeight(150);

        // Setup Observer for Notifications
        notifier = new BookingNotifier();
        notifier.addObserver(new CustomerObserver(notificationList));

        // Button Actions
        bookRideBtn.setOnAction(e -> new BookingUI().start(new Stage()));
        viewBookingsBtn.setOnAction(e -> new ViewBookingsUI().start(new Stage()));
        ongoingTripsBtn.setOnAction(e -> new OngoingTripsUI().start(new Stage()));
        profileBtn.setOnAction(e -> new ProfileUI().start(new Stage()));

        logoutBtn.setOnAction(e -> {
            new LoginUI().start(new Stage());  // Open Login Screen
            primaryStage.close();  // Close Customer Dashboard
        });

        VBox layout = new VBox(15, welcomeLabel, bookRideBtn, viewBookingsBtn, ongoingTripsBtn, profileBtn, notificationLabel, notificationView, logoutBtn);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Simulate a test notification (Remove in final version)
        Platform.runLater(() -> notifier.notifyObservers("🚕 Booking Confirmed! Driver assigned."));
    }

    public static void main(String[] args) {
        launch(args);
    }
}