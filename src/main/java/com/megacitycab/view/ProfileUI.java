package com.megacitycab.view;

import com.megacitycab.controller.CustomerController;
import com.megacitycab.model.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileUI extends Application {
    private final CustomerController customerController = new CustomerController();
    private Customer customer;

    public ProfileUI(Customer customer) {
        this.customer = customer;
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Profile UI");

        // Form for displaying customer profile
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        TextField idField = new TextField(String.valueOf(customer.getUserId()));
        idField.setEditable(false);
        TextField usernameField = new TextField(customer.getUsername());
        TextField passwordField = new TextField(customer.getPassword());
        TextField addressField = new TextField(customer.getAddress());
        TextField nicField = new TextField(customer.getNic());
        TextField phoneNumberField = new TextField(customer.getPhoneNumber());

        Button updateBtn = new Button("Update Profile");
        updateBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-size: 14px;");

        updateBtn.setOnAction(e -> {
            customer.setUsername(usernameField.getText());
            customer.setPassword(passwordField.getText());
            customer.setAddress(addressField.getText());
            customer.setNic(nicField.getText());
            customer.setPhoneNumber(phoneNumberField.getText());
            if (customerController.updateCustomer(customer)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Profile updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update profile.");
            }
        });

        Button deleteBtn = new Button("Delete Profile");
        deleteBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-size: 14px;");

        deleteBtn.setOnAction(e -> {
            if (customerController.deleteCustomer(customer.getUserId())) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Profile deleted successfully!");
                primaryStage.close();
                new UserSelectionUI().start(new Stage()); // Redirect to user selection UI
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete profile.");
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-font-size: 14px;");
        backBtn.setOnAction(e -> {
            primaryStage.close();
            new CustomerDashboard(customer).start(new Stage());
        });

        form.add(new Label("Customer ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Username:"), 0, 1);
        form.add(usernameField, 1, 1);
        form.add(new Label("Password:"), 0, 2);
        form.add(passwordField, 1, 2);
        form.add(new Label("Address:"), 0, 3);
        form.add(addressField, 1, 3);
        form.add(new Label("NIC:"), 0, 4);
        form.add(nicField, 1, 4);
        form.add(new Label("Phone Number:"), 0, 5);
        form.add(phoneNumberField, 1, 5);
        form.add(updateBtn, 0, 6);
        form.add(deleteBtn, 1, 6);
        form.add(backBtn, 0, 7);

        VBox layout = new VBox(10, form);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        Customer sampleCustomer = new Customer(1, "john_doe", "password123", "123 Street", "123456789", "555-5555");
        new ProfileUI(sampleCustomer).start(new Stage());
    }
}