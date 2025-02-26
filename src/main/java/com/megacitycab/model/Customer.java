package com.megacitycab.model;

public class Customer {
    
    private String username;
    private String name;
    private String nic;
    private String phone;
    private String address;

    public Customer( String username, String name, String nic, String phone, String address ){
        this.username = username;
        this.name = name;
        this.nic = nic;
        this.phone = phone;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getNic() {
        return nic;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }

}
