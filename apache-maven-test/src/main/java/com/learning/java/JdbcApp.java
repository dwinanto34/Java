package com.learning.java;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigDecimal;
import java.sql.*;

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

    private static void executeCreateTable(Connection conn) throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS public.general_products (" +
                "id CHARACTER VARYING(36), " +
                "name CHARACTER VARYING(100), " +
                "description TEXT, " +
                "price NUMERIC(19, 2)" +
                ");";
        try (Statement stmt = conn.createStatement()) {
            int affected = stmt.executeUpdate(query);
            System.out.println("Create table: " + affected);
        }
    }

    private static void executeInsertData(Connection conn) throws Exception {
        String query = "INSERT INTO general_products(id, name, description, price) " +
                "VALUES " +
                "('0001', 'iPhone 1', 'this is iPhone 1', 1000), " +
                "('0002', 'iPhone 2', 'this is iPhone 2', 1000), " +
                "('0003', 'iPhone 3', 'this is iPhone 3', 1000);";
        try (Statement stmt = conn.createStatement()) {
            int affected = stmt.executeUpdate(query);
            System.out.println("Insert data: " + affected);
        }
    }

    private static void executePreparedStatement(Connection conn) throws Exception {
        String query = "INSERT INTO general_products(id, name, description, price) " +
                "VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "0004");
            stmt.setString(2, "iPhone 4");
            stmt.setString(3, "this is iPhone 4");
            stmt.setBigDecimal(4, new BigDecimal(1000));

            int affected = stmt.executeUpdate();
            System.out.println("Insert data using prepared statement: " + affected);
        }
    }

    private static void executeBatchInsertData(Connection conn) throws Exception {
        String query1 = "INSERT INTO general_products(id, name, description, price) " +
                "VALUES ('0005', 'iPhone 5', 'this is iPhone 5', 1000);";
        String query2 = "INSERT INTO general_products(id, name, description, price) " +
                "VALUES ('0006', 'iPhone 6', 'this is iPhone 6', 1000);";
        String query3 = "INSERT INTO general_products(id, name, description, price) " +
                "VALUES ('0007', 'iPhone 7', 'this is iPhone 7', 1000);";

        try (Statement stmt = conn.createStatement()) {
            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            int[] affected = stmt.executeBatch();
            System.out.println("Insert data using batch process: " + (affected[0] + affected[1] + affected[2]));
        }
    }

    private static void executeGetData(Connection conn) throws Exception {
        String query = "SELECT * FROM general_products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Get data");

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                StringBuilder stringBuilder = new StringBuilder()
                        .append("ID: " + id)
                        .append(" Name: " + name)
                        .append(" Description: " + description)
                        .append(" Rating: " + price);
                System.out.println(stringBuilder);
            }
        }
    }

    private static void executeDropTable(Connection conn) throws Exception {
        String query = "DROP TABLE IF EXISTS public.general_products;";
        try (Statement stmt = conn.createStatement()) {
            int affected = stmt.executeUpdate(query);
            System.out.println("Drop table: " + affected);
        }
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

            executeCreateTable(conn);
            executeInsertData(conn);
            // insert data using prepared statement to prevent SQL injection
            executePreparedStatement(conn);
            // insert data using batch execution
            executeBatchInsertData(conn);
            executeGetData(conn);
            executeDropTable(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

