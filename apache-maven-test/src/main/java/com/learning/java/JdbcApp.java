package com.learning.java;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcApp {
    // HikariCP -- Connection pool
    // Creating a new database connection for each request is expensive and can lead to delays.
    // Connection pooling reuses existing connections from the pool, making it much faster and more efficient.
    // Connection pools limit the number of active connections, preventing database overloading.
    // It ensures that connections are properly reused without reaching the database server's maximum connections.
    private static HikariDataSource hikariDataSource;

    static {
        // Configure HikariCP
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/product");
        config.setUsername("postgres");
        config.setPassword("admin");
        config.setMaximumPoolSize(10);

        config.setMinimumIdle(2);
        config.setIdleTimeout(30 * 1_000); // 30 seconds
        config.setMaxLifetime(30 * 60 * 1_000); // 30 minutes
        config.setConnectionTimeout(30 * 1_000); // 30 seconds

        // Initialize the data source
        hikariDataSource = new HikariDataSource(config);
    }

    private static Connection getHikariConnection() throws Exception {
        return hikariDataSource.getConnection();
    }

    private static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/product";
        String username = "postgres";
        String password = "admin";

        return DriverManager.getConnection(url, username, password);
    }

    public static void run() {
        // JDBC Connection
        // try-with-resource will handle close connection automatically
        try (Connection conn = getHikariConnection()) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

