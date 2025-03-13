package com.megacitycab.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class ManagerObserver implements Observer {
    private ObservableList<String> notificationList;

    public ManagerObserver(ObservableList<String> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public void update(String message) {
        Platform.runLater(() -> notificationList.add("🔔 " + message)); // Adds notification to UI ListView
    }
}