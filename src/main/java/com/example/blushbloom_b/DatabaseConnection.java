package com.example.blushbloom_b;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/BB";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static void verifyConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection established successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database. Please check your configuration.");
            e.printStackTrace();
        }
    }

    /**
     * Fetch a list of products from the database by their IDs
     * @param productIds List of product IDs
     * @return List of Product objects
     */
    public static List<Product> getProductsFromDatabase(List<Integer> productIds) {
        List<Product> products = new ArrayList<>();

        if (productIds.isEmpty()) {
            System.out.println("No product IDs provided. Returning an empty list.");
            return products;
        }

        String query = "SELECT id, name, price, stock FROM products WHERE id IN (" + generatePlaceholders(productIds.size()) + ")";
        System.out.println("Executing query: " + query);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < productIds.size(); i++) {
                preparedStatement.setInt(i + 1, productIds.get(i));
                System.out.println("Set parameter " + (i + 1) + " to: " + productIds.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");

                    Product product = new Product(id, name, null, price, stock);
                    products.add(product);
                    System.out.println("Fetched product: " + product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products from database.");
            e.printStackTrace();
        }

        return products;
    }

    /**
     * Fetch a product by its name from the database
     * @param productName The name of the product
     * @return A Product object or null if not found
     */
    public static Product getProductByName(String productName) {
        Product product = null;

        String query = "SELECT id, name, price, stock FROM products WHERE name = ?";
        System.out.println("Executing query: " + query);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, productName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");

                    product = new Product(id, name, null, price, stock);
                    System.out.println("Fetched product by name: " + product);
                } else {
                    System.out.println("Product not found in database: " + productName);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product by name.");
            e.printStackTrace();
        }

        return product;
    }

    /**
     * Utility method to generate placeholders for SQL queries
     * @param count Number of placeholders needed
     * @return A string with placeholders separated by commas
     */
    private static String generatePlaceholders(int count) {
        return String.join(",", java.util.Collections.nCopies(count, "?"));
    }



    public static void main(String[] args) {
        System.out.println("Verifying database connection...");
        verifyConnection();

        List<Integer> productIds = List.of(1, 2, 3);
        List<Product> products = getProductsFromDatabase(productIds);

        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product);
        }


        Product product = getProductByName("Foundation");
        if (product != null) {
            System.out.println("Fetched product by name: " + product);
        } else {
            System.out.println("Product not found by name.");
        }
    }
}