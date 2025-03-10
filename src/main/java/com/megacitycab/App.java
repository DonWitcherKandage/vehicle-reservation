package com.megacitycab;

import com.megacitycab.controller.AuthController;
import com.megacitycab.dao.UserDAO;
import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.util.DatabaseManager;
import com.megacitycab.view.AuthUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // Start Spring Boot Application
    }

    @Bean
    CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            try {
                System.out.println("Starting Mega City Cab Application...");

                // Get Beans from Spring Context
                UserDAO userDAO = ctx.getBean(UserDAO.class);
                CustomerDAO customerDAO = ctx.getBean(CustomerDAO.class);
                AuthController authController = new AuthController(userDAO, customerDAO);
                AuthUI authUI = new AuthUI(authController);

                // Connect to Database
                DatabaseManager.getConnection();

                // Show the Auth Menu
                authUI.showAuthMenu();
            } catch (Exception e) {
                System.err.println("Application error: " + e.getMessage());
            } finally {
                DatabaseManager.closeConnection();
            }
        };
    }
}