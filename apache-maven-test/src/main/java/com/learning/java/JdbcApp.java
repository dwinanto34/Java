package com.learning.java;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcApp {
    private static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/product";
        String username = "postgres";
        String password = "admin";

        return DriverManager.getConnection(url, username, password);
    }

    public static void run() {
        // JDBC Connection
        // try-with-resource will handle close connection automatically
        try (Connection conn = getConnection()) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
