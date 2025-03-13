package com.megacitycab.model;

/**
 * Manager class extends User with a fixed "MANAGER" role.
 */
public class Manager extends User {

    // ✅ Correct constructor (Fixes the error)
    public Manager(int userId, String username, String password) {
        super(userId, username, password, "MANAGER"); // Passes all four required arguments
    }
}