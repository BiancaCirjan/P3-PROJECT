package com.example.blushbloom_b;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountManager accountManager = new AccountManager();
    private static final List<MakeupProduct> makeupStore = new ArrayList<>();
    private static String currentUser = null;

    public static void main(String[] args) {
        String mode = "client";
        if (args.length > 0) {
            mode = args[0].toLowerCase();
        }

        loadSampleProducts();
        System.out.println("Welcome to Blush&Bloom!");

        if (!userLogin(mode)) {
            System.out.println("Exiting the application.");
            accountManager.shutdownExecutor();
            return;
        }

        if (mode.equals("admin")) {
            adminMenu();
        } else if (mode.equals("client")) {
            userMenu();
        } else {
            System.out.println("Invalid mode specified.");
        }

        System.out.println("Thank you for visiting Blush&Bloom. Goodbye!");
        scanner.close();
        accountManager.shutdownExecutor();
    }

    private static boolean userLogin(String mode) {
        while (true) {
            System.out.println("1. Register\n2. Login");
            System.out.print("Choose an option: ");
            int choice = getUserChoice();

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (choice == 1) {
                if (mode.equals("admin")) {
                    System.out.print("Enter a 3-digit Admin ID: ");
                    int adminID;
                    try {
                        adminID = Integer.parseInt(scanner.nextLine());
                        if (!accountManager.registerAdmin(username, password, adminID)) {
                            System.out.println("Admin registration failed.");
                        } else {
                            currentUser = username;
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Admin ID must be a 3-digit number.");
                    }
                } else if (mode.equals("client")) {
                    accountManager.register(username, password);
                    currentUser = username;
                    return true;
                }
            } else if (choice == 2) {
                if (!accountManager.userExists(username)) {
                    System.out.println("Username not found. Please try again.");
                } else if (!accountManager.isPasswordCorrect(username, password)) {
                    System.out.println("Incorrect password. Please try again.");
                } else {
                    System.out.println("Login successful.");
                    currentUser = username;
                    accountManager.loadFavoritesAsync(currentUser);
                    return true;
                }
            } else {
                System.out.println("Invalid option. Please choose 1 to Register or 2 to Login.");
            }

            System.out.print("Do you want to try again? (yes/no): ");
            String retry = scanner.nextLine().trim().toLowerCase();
            if (!retry.equals("yes")) {
                System.out.println("Exiting login.");
                return false;
            }
        }
    }

    private static void userMenu() {
        boolean exit = false;
        while (!exit) {
            displayUserMenu();
            int choice = getUserChoice();

            try {
                switch (choice) {
                    case 1 -> viewProductsByCategory();
                    case 2 -> displayLikedProducts();
                    case 3 -> displayDiscountedProducts();
                    case 4 -> exit = true;
                    default -> System.out.println("Please choose a valid option.");
                }
            } catch (InvalidNrException | OutOfStockException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    private static void viewProductsByCategory() throws InvalidNrException, OutOfStockException {
        System.out.println("\nPlease choose a category to view products:");
        System.out.println("1. Face\n2. Eyes\n3. Lips\n4. Brows\n5. Makeup Brushes\n6. Return to Main Menu");
        System.out.print("Choose an option: ");

        int categoryChoice = getUserChoice();
        if (categoryChoice < 1 || categoryChoice > 6) {
            throw new InvalidNrException("Invalid category selection.");
        }

        switch (categoryChoice) {
            case 1 -> displayProductsByCategory(Face.class);
            case 2 -> displayProductsByCategory(Eyes.class);
            case 3 -> displayProductsByCategory(Lips.class);
            case 4 -> displayProductsByCategory(Brows.class);
            case 5 -> displayProductsByCategory(MakeupBrushes.class);
            case 6 -> System.out.println("Returning to Main Menu.");
        }
    }

    private static void displayProductsByCategory(Class<?> category) {
        boolean found = false;
        for (MakeupProduct product : makeupStore) {
            if (category.isInstance(product)) {
                System.out.println("Name: " + product.getName());
                System.out.println("Details: " + product.getDetails());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Stock: " + product.getStock() + " available");

                System.out.print("Add to favorites (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("yes") && currentUser != null) {
                    accountManager.addFavorite(currentUser, product.getName());
                    System.out.println(product.getName() + " has been added to your favorites.");
                }

                found = true;
                System.out.println();
            }
        }
        if (!found) {
            System.out.println("No products available in this category.");
        }
    }


    private static int getUserChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
        }
        scanner.nextLine();
        return choice;
    }

    private static void loadSampleProducts() {
        makeupStore.add(new Face("Hydrating Foundation", "Sephora", 25.99, 50, "Dry", "Medium"));
        makeupStore.add(new Eyes("Waterproof Mascara", "Maybelline", 10.99, 30, "Black", true));
        makeupStore.add(new Lips("Matte Lipstick", "MAC", 18.99, 75, "Ruby Red", "Matte"));
        makeupStore.add(new Brows("Eyebrow Pencil", "Benefit", 15.99, 60, "Dark Brown", "Pencil"));
        makeupStore.add(new MakeupBrushes("Blending Brush", "Morphe", 12.99, 30, "Blending", "Synthetic"));
    }

    private static void displayUserMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. View Products by Category");
        System.out.println("2. View Liked Products");
        System.out.println("3. View Discounted Products");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void displayLikedProducts() {
        System.out.println("\nLiked Products:");
        if (currentUser != null) {
            Set<String> favorites = accountManager.getUserFavorites(currentUser);
            if (favorites.isEmpty()) {
                System.out.println("No liked products.");
            } else {
                for (String productName : favorites) {
                    System.out.println("Name: " + productName);
                }
            }
        } else {
            System.out.println("No user is logged in.");
        }
    }

    private static void displayDiscountedProducts() {
        System.out.println("\nDiscounted Products:");
        boolean found = false;
        for (MakeupProduct product : makeupStore) {
            if (product.isDiscounted()) {
                System.out.println("Name: " + product.getName());
                System.out.println("Details: " + product.getDetails());
                System.out.println("Discounted Price: $" + product.getPrice() + "\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No discounted products available.");
        }
    }

    private static void adminMenu() {
        boolean exit = false;
        while (!exit) {
            displayAdminMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> discountProduct();
                case 4 -> exit = true;
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void displayAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Apply Discount to a Product");
        System.out.println("4. Exit Admin Menu");
        System.out.print("Choose an option: ");
    }

    private static void addProduct() {
        System.out.print("Enter product details to add:\nName: ");
        String name = scanner.nextLine();
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter category (Face, Eyes, Lips, Brows, Brushes): ");
        String category = scanner.nextLine().toLowerCase();

        MakeupProduct newProduct = switch (category) {
            case "face" -> new Face(name, brand, price, stock, "All Skin Types", "Medium");
            case "eyes" -> new Eyes(name, brand, price, stock, "Black", true);
            case "lips" -> new Lips(name, brand, price, stock, "Red", "Matte");
            case "brows" -> new Brows(name, brand, price, stock, "Brown", "Pencil");
            case "brushes" -> new MakeupBrushes(name, brand, price, stock, "Blending", "Synthetic");
            default -> null;
        };

        if (newProduct != null) {
            makeupStore.add(newProduct);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Invalid category. Product not added.");
        }
    }

    private static void removeProduct() {
        System.out.print("Enter the product name to remove: ");
        String productName = scanner.nextLine();

        boolean removed = makeupStore.removeIf(product -> product.getName().equalsIgnoreCase(productName));
        System.out.println(removed ? "Product removed successfully." : "Product not found.");
    }

    private static void discountProduct() {
        System.out.print("Enter the product name to apply discount: ");
        String productName = scanner.nextLine();
        System.out.print("Enter discount percentage: ");
        double discountPercentage = scanner.nextDouble();
        scanner.nextLine();

        for (MakeupProduct product : makeupStore) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.applyDiscount(discountPercentage);
                System.out.println("Discount applied successfully to " + productName);
                return;
            }
        }
        System.out.println("Product not found.");
    }
}
