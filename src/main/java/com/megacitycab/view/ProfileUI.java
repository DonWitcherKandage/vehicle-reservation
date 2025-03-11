package com.megacitycab.view;

import com.megacitycab.controller.ProfileController;
import com.megacitycab.model.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileUI extends Application {
    private ProfileController profileController;
    private Customer loggedInCustomer; // Stores the current customer details

    public ProfileUI(Customer customer) {
        this.loggedInCustomer = customer;
        this.profileController = new ProfileController();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Profile");

        Label titleLabel = new Label("My Profile");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(loggedInCustomer.getUsername());
        usernameField.setEditable(false);

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField(loggedInCustomer.getAddress());
        addressField.setEditable(false);

        Label nicLabel = new Label("NIC:");
        TextField nicField = new TextField(loggedInCustomer.getNic());
        nicField.setEditable(false);

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField(loggedInCustomer.getPhoneNumber());
        phoneField.setEditable(false);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10, titleLabel, usernameLabel, usernameField, addressLabel, addressField, 
                               nicLabel, nicField, phoneLabel, phoneField, closeButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}