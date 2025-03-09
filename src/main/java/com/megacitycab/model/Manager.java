package com.megacitycab.model;

public class Manager extends User {
    private String managerID;

    public Manager(String userID, String userName, String password, String managerID) {
        super(userID, userName, password);
        this.managerID = managerID;
    }
}