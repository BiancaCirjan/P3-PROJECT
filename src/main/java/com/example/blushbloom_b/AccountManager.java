package com.example.blushbloom_b;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountManager {
    private final String filePath = "accounts.txt";
    private Map<String, String> accounts = new HashMap<>();
    private Map<String, Set<String>> userFavorites = new HashMap<>();
    private Set<Integer> adminIDs = new HashSet<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public AccountManager() {
        loadAccounts();
    }

    public boolean registerAdmin(String username, String password, int adminID) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists.");
            return false;
        }

        if (adminID < 100 || adminID > 999) {
            System.out.println("Invalid Admin ID. It must be a 3-digit number.");
            return false;
        }

        if (adminIDs.contains(adminID)) {
            System.out.println("Admin ID already in use. Please choose a different ID.");
            return false;
        }

        accounts.put(username, password);
        adminIDs.add(adminID);
        saveAccountAsync(username, password, adminID);
        userFavorites.put(username, new HashSet<>());
        System.out.println("Admin registered with ID: " + adminID);
        return true;
    }

    public boolean register(String username, String password) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists.");
            return false;
        }
        accounts.put(username, password);
        saveAccountAsync(username, password, -1);
        userFavorites.put(username, new HashSet<>());
        return true;
    }

    private int generateUniqueAdminID() {
        Random random = new Random();
        int id;
        do {
            id = 100 + random.nextInt(900);
        } while (adminIDs.contains(id));
        return id;
    }

    private void saveAccountAsync(String username, String password, int adminID) {
        executor.submit(() -> {
            try (FileWriter writer = new FileWriter(filePath, true)) {
                writer.write(username + "," + password + (adminID >= 0 ? "," + adminID : "") + "\n");
                System.out.println("Account saved for: " + username);
            } catch (IOException e) {
                System.out.println("Error saving account: " + e.getMessage());
            }
        });
    }

    private void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                accounts.put(parts[0], parts[1]);
                if (parts.length > 2) {
                    int adminID = Integer.parseInt(parts[2]);
                    adminIDs.add(adminID);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public boolean userExists(String username) {
        return accounts.containsKey(username);
    }

    public boolean isPasswordCorrect(String username, String password) {
        return password.equals(accounts.get(username));
    }

    public boolean isAdmin(String username) {
        return adminIDs.stream().anyMatch(id -> accounts.get(username) != null);
    }

    public Set<String> getUserFavorites(String username) {
        return userFavorites.computeIfAbsent(username, k -> new HashSet<>());
    }

    public void addFavorite(String username, String productName) {
        Set<String> favorites = getUserFavorites(username);
        if (favorites.add(productName)) {
            saveFavoritesAsync(username);
        }
    }

    private void saveFavoritesAsync(String username) {
        executor.submit(() -> {
            Set<String> favorites = userFavorites.get(username);
            if (favorites != null) {
                try (PrintWriter writer = new PrintWriter(new FileWriter("favorites_" + username + ".txt"))) {
                    for (String productName : favorites) {
                        writer.println(productName);
                    }
                    System.out.println("Favorites saved for: " + username);
                } catch (IOException e) {
                    System.out.println("Error saving favorites for " + username + ": " + e.getMessage());
                }
            }
        });
    }

    public void loadFavorites(String username) {
        Set<String> favorites = new HashSet<>();
        File file = new File("favorites_" + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    favorites.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error loading favorites for " + username + ": " + e.getMessage());
            }
        }
        userFavorites.put(username, favorites);
    }

    public void loadFavoritesAsync(String username) {
        executor.submit(() -> {
            Set<String> favorites = new HashSet<>();
            File file = new File("favorites_" + username + ".txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        favorites.add(line);
                    }
                    System.out.println("Favorites loaded for: " + username);
                } catch (IOException e) {
                    System.out.println("Error loading favorites for " + username + ": " + e.getMessage());
                }
            }
            userFavorites.put(username, favorites);
        });
    }


    public void shutdownExecutor() {
        executor.shutdown();
        System.out.println("Executor service shut down.");
    }
}
