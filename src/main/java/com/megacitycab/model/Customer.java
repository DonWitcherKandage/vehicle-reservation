package com.megacitycab.model;

/**
 * Customer class extends User and adds customer-specific attributes.
 */
public class Customer extends User {
    private String address;
    private String nic;
    private String phoneNumber;

    // ✅ Correct constructor with int userId
    public Customer(int userId, String username, String password, String address, String nic, String phoneNumber) {
        super(userId, username, password, "CUSTOMER");
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getAddress() { return address; }
    public String getNic() { return nic; }
    public String getPhoneNumber() { return phoneNumber; }

    // Setters
    public void setAddress(String address) { this.address = address; }
    public void setNic(String nic) { this.nic = nic; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}