package com.example.blushbloom_b;

public class FavouritesControllerTest {

    public static void main(String[] args) {
        testAddToFavourites();
        testRemoveFromFavourites();
        testFavouriteListContents();
    }

    public static void testAddToFavourites() {
        Product product1 = new Product(1, "Eyeliner", 12.99, 100);

        _favouritesController.addToFavourites(product1);

        if (_favouritesController.getFavourites().contains(product1)) {
            System.out.println("testAddToFavourites passed!");
        } else {
            System.out.println("testAddToFavourites failed!");
        }
    }

    public static void testRemoveFromFavourites() {
        Product product1 = new Product(1, "Eyeliner", 12.99, 100);
        _favouritesController.addToFavourites(product1);

        _favouritesController.getFavourites().remove(product1);

        if (!_favouritesController.getFavourites().contains(product1)) {
            System.out.println("testRemoveFromFavourites passed!");
        } else {
            System.out.println("testRemoveFromFavourites failed!");
        }
    }

    public static void testFavouriteListContents() {
        Product product1 = new Product(1, "Eyeliner", 12.99, 100);
        Product product2 = new Product(2, "Mascara", 14.99, 80);

        _favouritesController.addToFavourites(product1);
        _favouritesController.addToFavourites(product2);

        if (_favouritesController.getFavourites().size() == 2 &&
                _favouritesController.getFavourites().contains(product1) &&
                _favouritesController.getFavourites().contains(product2)) {
            System.out.println("testFavouriteListContents passed!");
        } else {
            System.out.println("testFavouriteListContents failed!");
        }
    }
}
