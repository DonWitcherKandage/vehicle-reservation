package com.megacitycab.model;

public class Customer extends User {
    private String address;
    private String nic;
    private String phoneNumber;

    public Customer(String userId, String username, String password, String address, String nic, String phoneNumber) {
        super(userId, username, password, "CUSTOMER");
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() { return address; }
    public String getNic() { return nic; }
    public String getPhoneNumber() { return phoneNumber; }
}