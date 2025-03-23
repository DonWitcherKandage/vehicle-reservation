package com.megacitycab.view;

import com.megacitycab.controller.ManagerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagerDashboard {
    private final ManagerController controller;

    public ManagerDashboard(ManagerController controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manager Dashboard");
        primaryStage.setResizable(false);

        // UI Components
        Text header = new Text("Management Console");
        header.setFont(Font.font("Arial", 20));

        Button vehiclesBtn = createStyledButton("Manage Vehicles", "#4682B4");
        Button driversBtn = createStyledButton("Manage Drivers", "#4682B4");
        Button reportsBtn = createStyledButton("Generate Reports", "#4682B4");
        Button logoutBtn = createStyledButton("Logout", "#DC143C");

        // Button Actions
        vehiclesBtn.setOnAction(e -> controller.manageVehicles());
        driversBtn.setOnAction(e -> controller.manageDrivers());
        reportsBtn.setOnAction(e -> controller.generateReport());
        logoutBtn.setOnAction(e -> controller.logout());

        // Layout
        VBox layout = new VBox(15, header, vehiclesBtn, driversBtn, reportsBtn, logoutBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #F8F8FF;");

        primaryStage.setScene(new Scene(layout, 450, 400));
        primaryStage.show();
    }

    private Button createStyledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 200px;");
        return btn;
    }
}