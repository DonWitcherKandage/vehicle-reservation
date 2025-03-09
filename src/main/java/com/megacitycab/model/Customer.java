package com.megacitycab.model;

/** Represents a Customer (inherits from User) */
public class Customer extends User {
    private String customerAddress;
    private String nic;
    private String phoneNumber;

    /** Corrected constructor */
    public Customer(String userID, String userName, String password, String customerAddress, String nic, String phoneNumber) {
        super(userID, userName, password); // Calls User class constructor
        this.customerAddress = customerAddress;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
    }

    /** Getters */
    public String getCustomerAddress() { return customerAddress; }
    public String getNic() { return nic; }
    public String getPhoneNumber() { return phoneNumber; }
}
