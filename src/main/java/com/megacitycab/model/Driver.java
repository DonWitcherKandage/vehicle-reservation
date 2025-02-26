package com.megacitycab.model;

public class Driver {
    
    private String diverId;
    private String name;
    private String licenseNumber;
    private String contact;

    public Driver( String driverId, String name, String licenseNumber, String contcat ){
        this.diverId = driverId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.contact = contcat;
    }

    public String getDriverId() {
        return diverId;
    }
    public String getName() {
        return name;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public String getContact() {
        return contact;
    }

}
