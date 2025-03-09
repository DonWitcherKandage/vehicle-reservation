package com.megacitycab;

import com.megacitycab.controller.AuthController;
import com.megacitycab.dao.UserDAO;
import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.util.DatabaseManager;
import com.megacitycab.view.AuthUI;

public class App {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            AuthController authController = new AuthController(userDAO, customerDAO); // Fixed constructor
            AuthUI authUI = new AuthUI(authController); // ✅ Pass AuthController

            DatabaseManager.getConnection();
            authUI.showAuthMenu(); // ✅ Fixed method call
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
        } finally {
            DatabaseManager.closeConnection();
        }
    }
}