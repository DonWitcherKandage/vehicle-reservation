package com.megacitycab.model;

import javafx.beans.property.*;

public class Customer extends User {
    private final IntegerProperty userId;
    private final StringProperty username;
    private final StringProperty address;
    private final StringProperty nic;
    private final StringProperty phoneNumber;

    // ✅ Correct constructor with int userId
    public Customer(int userId, String username, String password, String address, String nic, String phoneNumber) {
        super(userId, username, password, "CUSTOMER");
        this.userId = new SimpleIntegerProperty(userId);
        this.username = new SimpleStringProperty(username);
        this.address = new SimpleStringProperty(address);
        this.nic = new SimpleStringProperty(nic);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    // Getters
    public int getUserId() { return userId.get(); }
    public String getUsername() { return username.get(); }
    public String getAddress() { return address.get(); }
    public String getNic() { return nic.get(); }
    public String getPhoneNumber() { return phoneNumber.get(); }

    // Setters
    public void setUserId(int userId) { this.userId.set(userId); }
    public void setUsername(String username) { this.username.set(username); }
    public void setAddress(String address) { this.address.set(address); }
    public void setNic(String nic) { this.nic.set(nic); }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }

    // JavaFX Property Getters
    public IntegerProperty userIdProperty() { return userId; }
    public StringProperty usernameProperty() { return username; }
    public StringProperty addressProperty() { return address; }
    public StringProperty nicProperty() { return nic; }
    public StringProperty phoneNumberProperty() { return phoneNumber; }
}