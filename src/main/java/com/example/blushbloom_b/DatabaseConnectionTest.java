package com.example.blushbloom_b;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        testDatabaseConnection();
        testFetchProducts();
        testInsertProduct();
    }

    public static void testDatabaseConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("testDatabaseConnection passed!");
            } else {
                System.out.println("testDatabaseConnection failed!");
            }
        } catch (SQLException e) {
            System.out.println("testDatabaseConnection failed with exception: " + e.getMessage());
        }
    }

    public static void testFetchProducts() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            if (resultSet.next()) {
                System.out.println("testFetchProducts passed!");
            } else {
                System.out.println("testFetchProducts failed! No products found.");
            }
        } catch (SQLException e) {
            System.out.println("testFetchProducts failed with exception: " + e.getMessage());
        }
    }

    public static void testInsertProduct() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String insertQuery = "INSERT INTO products (id, name, price, stock) VALUES (100, 'Test Product', 9.99, 10)";
            int rowsAffected = statement.executeUpdate(insertQuery);

            if (rowsAffected > 0) {
                System.out.println("testInsertProduct passed!");
            } else {
                System.out.println("testInsertProduct failed!");
            }
        } catch (SQLException e) {
            System.out.println("testInsertProduct failed with exception: " + e.getMessage());
        }
    }
}
