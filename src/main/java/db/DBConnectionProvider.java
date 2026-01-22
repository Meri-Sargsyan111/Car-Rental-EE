package db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnectionProvider {
    @Getter
    private static DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;
    private static boolean tablesInitialized = false;

    private final String DB_NAME = "car_rental_db";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private final String JDBC_URL_NO_DB = "jdbc:mysql://localhost:3306/";
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "root";

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
                } catch (SQLException e) {
                    if (e.getMessage() != null && e.getMessage().contains("Unknown database")) {
                        createDatabaseIfNotExists();
                        connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
                    } else {
                        throw e;
                    }
                }
            }
            if (connection == null) {
                throw new RuntimeException("Failed to establish database connection");
            }
            if (!tablesInitialized) {
                initializeTables();
                tablesInitialized = true;
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    private void createDatabaseIfNotExists() {
        try (Connection tempConn = DriverManager.getConnection(JDBC_URL_NO_DB, JDBC_USERNAME, JDBC_PASSWORD);
             Statement stmt = tempConn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            // Initialize tables after database creation
            initializeTables();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create database: " + DB_NAME, e);
        }
    }

    private void initializeTables() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create car table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS car (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "BREND VARCHAR(100) NOT NULL, " +
                    "MODEL VARCHAR(100) NOT NULL, " +
                    "YEAR INT NOT NULL, " +
                    "DAILY_RATE DECIMAL(10, 2) NOT NULL, " +
                    "STATUS VARCHAR(20) NOT NULL" +
                    ")");

            // Create customer table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS customer (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "NAME VARCHAR(100) NOT NULL, " +
                    "SURNAME VARCHAR(100) NOT NULL, " +
                    "LICENSE_NUMBER VARCHAR(50) NOT NULL, " +
                    "PHONE VARCHAR(20), " +
                    "EMAIL VARCHAR(100), " +
                    "STATUS VARCHAR(20) NOT NULL" +
                    ")");

            // Create rental table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS rental (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "car_id INT NOT NULL, " +
                    "customer_id INT NOT NULL, " +
                    "start_date DATE NOT NULL, " +
                    "end_date DATE NOT NULL, " +
                    "total_cost DECIMAL(10, 2) NOT NULL, " +
                    "status VARCHAR(20) NOT NULL, " +
                    "FOREIGN KEY (car_id) REFERENCES car(ID), " +
                    "FOREIGN KEY (customer_id) REFERENCES customer(ID)" +
                    ")");
        } catch (SQLException e) {

            System.out.println("Note: Tables may already exist or foreign keys need to be created in order: " + e.getMessage());
        }
    }


}
