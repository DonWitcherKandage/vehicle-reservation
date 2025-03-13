package com.megacitycab.util;

import java.util.ArrayList;
import java.util.List;

public class BookingNotifier {
    private final List<Observer> observers = new ArrayList<>();

    // ✅ Add an observer (Customer, Manager, etc.)
    public void addObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null!");
        }
        observers.add(observer);
    }

    // ✅ Remove an observer
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // ✅ Notify all observers when booking status changes
    public void notifyObservers(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Notification message cannot be empty!");
        }

        for (Observer observer : observers) {
            observer.update("📢 Notification: " + message);
        }
    }
}