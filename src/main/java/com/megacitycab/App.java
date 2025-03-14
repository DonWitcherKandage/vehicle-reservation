package com.megacitycab;

import com.megacitycab.view.UserSelectionUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point of the application
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        UserSelectionUI userSelectionUI = new UserSelectionUI();
        userSelectionUI.start(primaryStage); // Load the user selection first
    }

    public static void main(String[] args) {
        launch(args);
    }
}