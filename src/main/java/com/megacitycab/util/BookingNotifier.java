package com.megacitycab.util;

import java.util.ArrayList;
import java.util.List;

public class BookingNotifier {
    private List<Observer> observers = new ArrayList<>();

    // Add an observer (customer, manager, etc.)
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Notify all observers when booking status changes
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}