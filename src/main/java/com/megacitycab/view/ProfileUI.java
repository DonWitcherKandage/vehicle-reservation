package com.megacitycab.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class ProfileUI extends Application {
    public ProfileUI() {
        start(new Stage()); // Ensure proper initialization
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Profile UI");
        primaryStage.show();
    }
}