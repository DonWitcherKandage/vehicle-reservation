package com.megacitycab.util;

import com.megacitycab.model.Customer;
import com.megacitycab.model.User;

public class UserFactory {
    public static User createUser(String role, int userId, String username, String password, String address, String nic, String phoneNumber) {
        if ("CUSTOMER".equalsIgnoreCase(role)) {
            return new Customer(userId, username, password, address, nic, phoneNumber);
        }
        return new User(userId, username, password, role);
    }
}