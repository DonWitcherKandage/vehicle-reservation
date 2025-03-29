package com.megacitycab.model;

public class Manager extends User {
    private String address;
    private String nic;
    private String phoneNumber;

    public Manager(int userId, String username, String password, String address, String nic, String phoneNumber) {
        super(userId, username, password, "MANAGER");
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
    }

    // Existing three-argument constructor if needed
    public Manager(int userId, String username, String password) {
        super(userId, username, password, "MANAGER");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}