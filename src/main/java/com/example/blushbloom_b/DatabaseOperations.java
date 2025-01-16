package com.example.blushbloom_b;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseOperations {

    public static void fetchFavourites(FavouritesCallback callback) {
        Task<Void> fetchTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try (Connection connection = DatabaseConnection.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM favourites")) {

                    while (resultSet.next()) {
                        String productName = resultSet.getString("name");


                        Platform.runLater(() -> callback.onFavouriteFetched(productName));
                    }
                }
                return null;
            }
        };

        Thread thread = new Thread(fetchTask);
        thread.setDaemon(true);
        thread.start();
    }

    public static void syncStockData() {

    }


    public interface FavouritesCallback {
        void onFavouriteFetched(String productName);
    }

    public static void addProductToCart(int productId, String productName, double price, int quantity) {
        Task<Void> insertTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try (Connection connection = DatabaseConnection.getConnection();
                     Statement statement = connection.createStatement()) {

                    String query = "INSERT INTO cart (product_id, name, price, quantity) VALUES ("
                            + productId + ", '" + productName + "', " + price + ", " + quantity + ")";
                    statement.executeUpdate(query);

                    Platform.runLater(() -> {
                        System.out.println("Added " + productName + " to cart!");
                    });
                }
                return null;
            }
        };

        Thread thread = new Thread(insertTask);
        thread.setDaemon(true);
        thread.start();
    }

    private static final long SYNC_INTERVAL = 300000;

    public static void startSyncing() {
        Thread syncThread = new Thread(() -> {
            while (true) {
                try {

                    syncDataFromDatabase();
                    Thread.sleep(SYNC_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        syncThread.setDaemon(true);
        syncThread.start();
    }

    private static void syncDataFromDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                int stock = resultSet.getInt("stock");
                System.out.println("Synced product: " + productName + ", Stock: " + stock);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
