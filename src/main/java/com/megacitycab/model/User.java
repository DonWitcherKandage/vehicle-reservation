package com.megacitycab.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/** Represents a User in the system */
public abstract class User {
    protected String userID;
    protected String userName;
    private String password;  // Kept private for security

    /** Regex Patterns */
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{5,20}$"); // Alphanumeric, 5-20 chars
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$"); // Strong password: Min 8 chars, 1 uppercase, 1 lowercase, 1 number, 1 special char
    private static final Pattern USERID_PATTERN = Pattern.compile("^USR\\d{5}$"); // Format: USR12345 (USR + 5 digits)

    /** Constructor with regex validation */
    public User(String userID, String userName, String password) {
        if (!USERID_PATTERN.matcher(userID).matches()) throw new IllegalArgumentException("Invalid User ID! Format: USR12345");
        if (!USERNAME_PATTERN.matcher(userName).matches()) throw new IllegalArgumentException("Invalid Username! Must be 5-20 chars (A-Z, 0-9, _, -, .)");
        if (!PASSWORD_PATTERN.matcher(password).matches()) throw new IllegalArgumentException("Weak Password! Must be 8+ chars, 1 uppercase, 1 lowercase, 1 number, 1 special char");

        this.userID = userID;
        this.userName = userName;
        this.password = hashPassword(password); // Store only hashed password
    }

    /** Secure password verification */
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(hashPassword(inputPassword)); // Hash input and compare
    }

    /** Getters */
    public String getUserID() { return userID; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }  // This is needed in UserDAO.java

    /** Secure Password Setter */
    public void setPassword(String newPassword) {
        if (!PASSWORD_PATTERN.matcher(newPassword).matches()) throw new IllegalArgumentException("Weak Password! Must be 8+ chars, 1 uppercase, 1 lowercase, 1 number, 1 special char");
        this.password = hashPassword(newPassword);
    }

    /** Password Hashing Function */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}