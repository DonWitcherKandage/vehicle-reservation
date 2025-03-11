package com.megacitycab.util;

import com.megacitycab.model.Customer;
import com.megacitycab.model.Manager;
import com.megacitycab.model.User;

public class UserFactory {
    public static User createUser(String type, String userID, String userName, String password) {
        if (type.equalsIgnoreCase("CUSTOMER")) {
            return new Customer(userID, userName, password, "Address Placeholder", "NIC Placeholder", "Phone Placeholder");
        } else if (type.equalsIgnoreCase("MANAGER")) {
            return new Manager(userID, userName, password, "Manager ID Placeholder");
        }
        return null;
    }
}