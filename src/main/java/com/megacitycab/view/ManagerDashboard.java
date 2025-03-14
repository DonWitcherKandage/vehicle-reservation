package com.megacitycab.view;

import com.megacitycab.controller.BookingController;
import com.megacitycab.model.Booking;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ManagerDashboard extends Application {
    private BookingController bookingController;
    private TableView<Booking> bookingTable;

    @Override
    public void start(Stage primaryStage) {
        bookingController = new BookingController();

        primaryStage.setTitle("Manager Dashboard");

        // Table Setup
        bookingTable = new TableView<>();
        TableColumn<Booking, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDestination()));

        TableColumn<Booking, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<Booking, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTime()));

        TableColumn<Booking, String> vehicleTypeColumn = new TableColumn<>("Vehicle Type");
        vehicleTypeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVehicleType()));

        TableColumn<Booking, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));

        bookingTable.getColumns().addAll(destinationColumn, dateColumn, timeColumn, vehicleTypeColumn, priceColumn);

        // Load Bookings
        loadBookings();

        VBox layout = new VBox(10, bookingTable);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadBookings() {
        List<Booking> pendingBookings = bookingController.getPendingBookings();
        ObservableList<Booking> bookings = FXCollections.observableArrayList(pendingBookings);
        bookingTable.setItems(bookings);
    }

    public static void main(String[] args) {
        launch(args);
    }
}