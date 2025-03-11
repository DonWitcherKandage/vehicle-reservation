package com.megacitycab.model;

public abstract class User {
    protected String userId;
    protected String username;
    protected String password;
    protected String role;  // "CUSTOMER" or "MANAGER"

    public User(String userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}