package com.megacitycab;

import com.megacitycab.util.DatabaseManager;
import java.sql.Connection;

public class TestDBConnection {
    
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseManager.getConnection();
            if (conn != null) {
                System.out.println("Database connection successful!");
        
            } 
            DatabaseManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
