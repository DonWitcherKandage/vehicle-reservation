package com.megacitycab.view;

//import com.megacitycab.view.BookingUI;
import com.megacitycab.util.BookingNotifier;
import com.megacitycab.util.ManagerObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerDashboard extends Application {
    private ObservableList<String> notificationList = FXCollections.observableArrayList();
    private BookingNotifier notifier;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manager Dashboard");

        Label welcomeLabel = new Label("Welcome, Manager!");

        Button manageVehiclesBtn = new Button("🚗 Manage Vehicles");
        Button manageDriversBtn = new Button("🧑‍✈️ Manage Drivers");
        Button viewBookingsBtn = new Button("📋 View & Assign Bookings");
        Button generateReportsBtn = new Button("📊 Generate Reports");
        Button logoutBtn = new Button("🚪 Logout");

        // Style Buttons
        manageVehiclesBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        manageDriversBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        viewBookingsBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        generateReportsBtn.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");
        logoutBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");

        // Notifications Section
        Label notificationLabel = new Label("🔔 Notifications:");
        ListView<String> notificationView = new ListView<>(notificationList);
        notificationView.setPrefHeight(150);

        // Setup Observer for Notifications
        notifier = new BookingNotifier();
        notifier.addObserver(new ManagerObserver(notificationList));

        // Button Actions
        manageVehiclesBtn.setOnAction(e -> new ManageVehiclesUI().start(new Stage()));
        manageDriversBtn.setOnAction(e -> new ManageDriversUI().start(new Stage()));
        viewBookingsBtn.setOnAction(e -> new BookingUI().start(new Stage()));
        generateReportsBtn.setOnAction(e -> new ReportUI().start(new Stage()));

        logoutBtn.setOnAction(e -> {
            new LoginUI().start(new Stage());  // Open Login Screen
            primaryStage.close();  // Close Manager Dashboard
        });

        VBox layout = new VBox(15, welcomeLabel, manageVehiclesBtn, manageDriversBtn, viewBookingsBtn, generateReportsBtn, notificationLabel, notificationView, logoutBtn);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Simulate a test notification (Remove in final version)
        Platform.runLater(() -> notifier.notifyObservers("🚕 New Booking Request! Assign a driver."));
    }

    public static void main(String[] args) {
        launch(args);
    }
}