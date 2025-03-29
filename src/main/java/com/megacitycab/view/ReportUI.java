package com.megacitycab.view;

import com.megacitycab.controller.BookingController;
import com.megacitycab.controller.DriverController;
import com.megacitycab.controller.VehicleController;
import com.megacitycab.model.Booking;
import com.megacitycab.model.Driver;
import com.megacitycab.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class ReportUI {
    private final Stage previousStage;
    private final BookingController bookingController;
    private final VehicleController vehicleController;
    private final DriverController driverController;

    public ReportUI(Stage previousStage) {
        this.previousStage = previousStage;
        this.bookingController = new BookingController();
        this.vehicleController = new VehicleController();
        this.driverController = new DriverController();
    }

    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Report Buttons
        Button bookingReportBtn = new Button("Generate Booking Report");
        Button vehicleReportBtn = new Button("Generate Vehicle Report");
        Button driverReportBtn = new Button("Generate Driver Report");
        Button backBtn = new Button("⬅ Back");

        // Styling
        bookingReportBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        vehicleReportBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        driverReportBtn.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: #666; -fx-text-fill: white;");

        // Button Actions
        bookingReportBtn.setOnAction(e -> showBookingReport(primaryStage));
        vehicleReportBtn.setOnAction(e -> showVehicleReport(primaryStage));
        driverReportBtn.setOnAction(e -> showDriverReport(primaryStage));

        backBtn.setOnAction(e -> {
            primaryStage.close();
            previousStage.show();
        });

        root.getChildren().addAll(bookingReportBtn, vehicleReportBtn, driverReportBtn, backBtn);

        // Scene Setup
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Generate Reports");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Show the booking report
     */
    private void showBookingReport(Stage primaryStage) {
        // Create a new stage for the report
        Stage reportStage = new Stage();
        reportStage.setTitle("Booking Report");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        // Status Selection
        HBox statusBox = new HBox(10);
        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.getItems().addAll("All Bookings", "Pending Bookings", "Completed Bookings", "Cancelled Bookings");
        statusCombo.setValue("All Bookings");

        statusBox.getChildren().addAll(
                new Label("Status:"),
                statusCombo
        );

        Button generateBtn = new Button("Generate Report");
        generateBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Report content area
        VBox reportContent = new VBox(15);

        generateBtn.setOnAction(e -> {
            // Clear previous content
            reportContent.getChildren().clear();

            // Get bookings based on selection
            List<Booking> bookings;

            if (statusCombo.getValue().equals("Pending Bookings")) {
                bookings = bookingController.getPendingBookings();
            } else {
                bookings = bookingController.getPendingBookings();

                if (!statusCombo.getValue().equals("All Bookings")) {
                    String statusFilter = statusCombo.getValue().replace(" Bookings", "").toUpperCase();
                    bookings = bookings.stream()
                            .filter(b -> b.getStatus().equals(statusFilter))
                            .collect(Collectors.toList());
                }
            }

            // Calculate totals
            int totalBookings = bookings.size();
            double totalRevenue = bookings.stream()
                    .mapToDouble(Booking::getPrice)
                    .sum();

            // Display summary
            Label summaryLabel = new Label(String.format(
                    "Summary: %d bookings, Total Revenue: $%.2f",
                    totalBookings, totalRevenue));
            summaryLabel.setStyle("-fx-font-weight: bold");

            // Create booking table
            TableView<Booking> bookingTable = new TableView<>();

            TableColumn<Booking, Integer> idCol = new TableColumn<>("Booking ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

            TableColumn<Booking, String> destCol = new TableColumn<>("Destination");
            destCol.setCellValueFactory(new PropertyValueFactory<>("destination"));

            TableColumn<Booking, String> dateCol = new TableColumn<>("Date");
            dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

            TableColumn<Booking, String> timeCol = new TableColumn<>("Time");
            timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

            TableColumn<Booking, String> vehicleTypeCol = new TableColumn<>("Vehicle Type");
            vehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

            TableColumn<Booking, Double> priceCol = new TableColumn<>("Price");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            TableColumn<Booking, String> statusCol = new TableColumn<>("Status");
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

            TableColumn<Booking, Integer> customerCol = new TableColumn<>("Customer ID");
            customerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

            bookingTable.getColumns().addAll(
                    idCol, destCol, dateCol, timeCol, vehicleTypeCol, priceCol, statusCol, customerCol);

            // Add data to table
            ObservableList<Booking> bookingData = FXCollections.observableArrayList(bookings);
            bookingTable.setItems(bookingData);

            // Add to report content
            reportContent.getChildren().addAll(summaryLabel, bookingTable);
        });

        // Back button
        Button backBtn = new Button("Back to Report Selection");
        backBtn.setOnAction(e -> reportStage.close());

        root.getChildren().addAll(
                new Label("Booking Report"),
                statusBox,
                generateBtn,
                reportContent,
                backBtn
        );

        Scene scene = new Scene(root, 800, 600);
        reportStage.setScene(scene);
        reportStage.show();
    }

    /**
     * Show the vehicle report
     */
    private void showVehicleReport(Stage primaryStage) {
        // Create a new stage for the report
        Stage reportStage = new Stage();
        reportStage.setTitle("Vehicle Report");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        // Filter options
        HBox filterBox = new HBox(10);
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("All Types", "Sedan", "SUV", "Luxury", "Van");
        typeCombo.setValue("All Types");

        filterBox.getChildren().addAll(
                new Label("Vehicle Type:"),
                typeCombo
        );

        Button generateBtn = new Button("Generate Report");
        generateBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        // Report content area
        VBox reportContent = new VBox(15);

        generateBtn.setOnAction(e -> {
            try {
                // Clear previous content
                reportContent.getChildren().clear();

                // Get vehicles from the vehicle controller
                ObservableList<Vehicle> vehicles = vehicleController.getAllVehicles();

                // Debug output
                System.out.println("Found " + vehicles.size() + " vehicles in database");

                // Filter by type if not "All Types"
                String selectedType = typeCombo.getValue();
                if (!selectedType.equals("All Types")) {
                    ObservableList<Vehicle> filteredVehicles = FXCollections.observableArrayList();
                    for (Vehicle v : vehicles) {
                        if (v.getType() != null && v.getType().equalsIgnoreCase(selectedType)) {
                            filteredVehicles.add(v);
                        }
                    }
                    vehicles = filteredVehicles;
                    System.out.println("After filtering for " + selectedType + ": " + vehicles.size() + " vehicles");
                }

                if (vehicles.isEmpty()) {
                    Label noDataLabel = new Label("No vehicles found matching the criteria");
                    noDataLabel.setStyle("-fx-text-fill: red;");
                    reportContent.getChildren().add(noDataLabel);
                    return;
                }

                // Calculate metrics
                int totalVehicles = vehicles.size();
                int availableVehicles = 0;
                for (Vehicle v : vehicles) {
                    if (v.isAvailable()) {
                        availableVehicles++;
                    }
                }

                // Display summary
                Label summaryLabel = new Label(String.format(
                        "Summary: %d vehicles found, %d available",
                        totalVehicles, availableVehicles));
                summaryLabel.setStyle("-fx-font-weight: bold");

                // Create vehicle table
                TableView<Vehicle> vehicleTable = new TableView<>();

                TableColumn<Vehicle, String> idCol = new TableColumn<>("ID");
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
                modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

                TableColumn<Vehicle, String> plateCol = new TableColumn<>("Plate Number");
                plateCol.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));

                TableColumn<Vehicle, String> typeCol = new TableColumn<>("Type");
                typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

                TableColumn<Vehicle, Double> rateCol = new TableColumn<>("Rate/km");
                rateCol.setCellValueFactory(new PropertyValueFactory<>("ratePerKm"));

                TableColumn<Vehicle, Boolean> availCol = new TableColumn<>("Available");
                availCol.setCellValueFactory(new PropertyValueFactory<>("available"));

                vehicleTable.getColumns().addAll(
                        idCol, modelCol, plateCol, typeCol, rateCol, availCol);

                vehicleTable.setItems(vehicles);

                // Add to report content
                reportContent.getChildren().addAll(summaryLabel, vehicleTable);

            } catch (Exception ex) {
                // Show error in the UI
                Label errorLabel = new Label("Error loading vehicle data: " + ex.getMessage());
                errorLabel.setStyle("-fx-text-fill: red;");
                reportContent.getChildren().add(errorLabel);

                // Print stack trace for debugging
                ex.printStackTrace();
            }
        });

        // Back button
        Button backBtn = new Button("Back to Report Selection");
        backBtn.setOnAction(e -> reportStage.close());

        root.getChildren().addAll(
                new Label("Vehicle Report"),
                filterBox,
                generateBtn,
                reportContent,
                backBtn
        );

        Scene scene = new Scene(root, 800, 600);
        reportStage.setScene(scene);
        reportStage.show();
    }

    /**
     * Show the driver report
     */
    private void showDriverReport(Stage primaryStage) {
        // Create a new stage for the report
        Stage reportStage = new Stage();
        reportStage.setTitle("Driver Report");

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        // Create driver table
        TableView<Driver> driverTable = new TableView<>();

        TableColumn<Driver, String> idCol = new TableColumn<>("Driver ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Driver, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Driver, Boolean> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));

        driverTable.getColumns().addAll(idCol, nameCol, availableCol);

        // Get drivers from the driver controller
        List<Driver> driverList = driverController.getAllDrivers();
        ObservableList<Driver> drivers = FXCollections.observableArrayList(driverList);
        driverTable.setItems(drivers);

        // Calculate metrics
        int totalDrivers = drivers.size();
        int availableDrivers = (int) drivers.stream().filter(Driver::isAvailable).count();

        // Display summary
        Label summaryLabel = new Label(String.format(
                "Summary: %d drivers found, %d available",
                totalDrivers, availableDrivers));
        summaryLabel.setStyle("-fx-font-weight: bold");

        // Back button
        Button backBtn = new Button("Back to Report Selection");
        backBtn.setOnAction(e -> reportStage.close());

        root.getChildren().addAll(
                new Label("Driver Report"),
                summaryLabel,
                driverTable,
                backBtn
        );

        Scene scene = new Scene(root, 800, 600);
        reportStage.setScene(scene);
        reportStage.show();
    }
}