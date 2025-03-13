package com.megacitycab.model;

public class Manager extends User {
    private String managerId;

    public Manager(String userId, String username, String password, String managerId) {
        super(userId, username, password, "MANAGER");
        this.managerId = managerId;
    }

    public String getManagerId() { return managerId; }
}