package com.megacitycab.util;

public class FormValidator {

    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]{5,15}$"); // 5-15 alphanumeric characters
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.[A-Z])(?=.\\d)(?=.*[@#$%^&+=]).{8,}$"); // At least 8 chars, 1 uppercase, 1 number, 1 special char
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\+?[0-9]{10,13}$"); // International format support
    }

    public static boolean isValidAddress(String address) {
        return address.length() >= 5 && address.length() <= 100;
    }
}