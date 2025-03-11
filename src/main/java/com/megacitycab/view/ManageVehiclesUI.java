package com.megacitycab.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ManageVehiclesUI extends Application {
    private ImageView vehicleImageView;

    @Override
    public void start(Stage primaryStage) {
        Label plateLabel = new Label("Vehicle Number Plate:");
        TextField plateField = new TextField();

        Label typeLabel = new Label("Vehicle Type:");
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("CAR", "VAN", "SUV");

        Label modelLabel = new Label("Vehicle Model:");
        TextField modelField = new TextField();

        Label priceLabel = new Label("Rate Per KM:");
        TextField priceField = new TextField();

        Button imageButton = new Button("Upload Image");
        vehicleImageView = new ImageView();
        vehicleImageView.setFitWidth(200);
        vehicleImageView.setPreserveRatio(true);

        imageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                vehicleImageView.setImage(image);
            }
        });

        Button addButton = new Button("Add Vehicle");

        VBox layout = new VBox(10, plateLabel, plateField, typeLabel, typeBox, modelLabel, modelField,
                priceLabel, priceField, imageButton, vehicleImageView, addButton);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage Vehicles");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}