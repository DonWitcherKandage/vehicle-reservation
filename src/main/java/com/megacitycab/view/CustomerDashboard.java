package com.megacitycab.view;

import com.megacitycab.util.BookingNotifier;
import com.megacitycab.util.CustomerObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Customer Dashboard UI
 */
public class CustomerDashboard extends Application {
    private ObservableList<String> notificationList = FXCollections.observableArrayList();
    private BookingNotifier notifier;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Dashboard");

        Label welcomeLabel = new Label("Welcome to MegaCity Cab!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // ✅ Buttons
        Button bookRideBtn = new Button("🚖 Book a Ride");
        Button viewBookingsBtn = new Button("📜 View My Bookings");
        Button ongoingTripsBtn = new Button("🛣️ Ongoing Trips");
        Button profileBtn = new Button("👤 View Profile");
        Button logoutBtn = new Button("🚪 Logout");

        // ✅ Apply consistent styling
        styleButton(bookRideBtn);
        styleButton(viewBookingsBtn);
        styleButton(ongoingTripsBtn);
        styleButton(profileBtn);
        styleLogoutButton(logoutBtn);

        // ✅ Notifications Section
        Label notificationLabel = new Label("🔔 Notifications:");
        notificationLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        ListView<String> notificationView = new ListView<>(notificationList);
        notificationView.setPrefHeight(150);

        // ✅ Observer Setup
        notifier = new BookingNotifier();
        notifier.addObserver(new CustomerObserver(notificationList));

        // ✅ Button Actions
        bookRideBtn.setOnAction(e -> new BookingUI().start(new Stage()));
        viewBookingsBtn.setOnAction(e -> new ViewBookingsUI().start(new Stage()));
        ongoingTripsBtn.setOnAction(e -> new OngoingTripsUI().start(new Stage()));
        profileBtn.setOnAction(e -> new ProfileUI().start(new Stage()));

        logoutBtn.setOnAction(e -> {
            new UserSelectionUI().start(new Stage()); // Takes user back to selection screen
            primaryStage.close();
        });

        // ✅ Layout
        VBox layout = new VBox(15, welcomeLabel, bookRideBtn, viewBookingsBtn, ongoingTripsBtn, profileBtn, notificationLabel, notificationView, logoutBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // ✅ Simulate a test notification (For Testing Only, Remove in Final)
        Platform.runLater(() -> notifier.notifyObservers("🚕 Booking Confirmed! Driver assigned."));
    }

    /**
     * Styles buttons for uniform appearance.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-font-size: 14px; -fx-pref-width: 250px; -fx-padding: 10px;");
    }

    /**
     * Styles logout button with red color.
     */
    private void styleLogoutButton(Button button) {
        button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 250px; -fx-padding: 10px;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}