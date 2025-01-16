package com.example.blushbloom_b;

public class CartControllerTest {

    public static void main(String[] args) {
        testAddToCart();
        testDeleteFromCart();
        testCartContents();
    }

    public static void testAddToCart() {
        Product product1 = new Product(1, "Lipstick", 19.99, 50);
        Product product2 = new Product(2, "Foundation", 29.99, 30);

        _cartController.addToCart(product1);
        _cartController.addToCart(product2);

        if (_cartController.getCart().contains(product1) && _cartController.getCart().contains(product2)) {
            System.out.println("testAddToCart passed!");
        } else {
            System.out.println("testAddToCart failed!");
        }
    }

    public static void testDeleteFromCart() {
        Product product1 = new Product(1, "Lipstick", 19.99, 50);
        Product product2 = new Product(2, "Foundation", 29.99, 30);

        _cartController.addToCart(product1);
        _cartController.addToCart(product2);
        _cartController.getCart().remove(product1);

        if (!_cartController.getCart().contains(product1) && _cartController.getCart().contains(product2)) {
            System.out.println("testDeleteFromCart passed!");
        } else {
            System.out.println("testDeleteFromCart failed!");
        }
    }

    public static void testCartContents() {
        Product product1 = new Product(1, "Lipstick", 19.99, 50);
        _cartController.addToCart(product1);

        if (_cartController.getCart().size() == 1 && _cartController.getCart().get(0).equals(product1)) {
            System.out.println("testCartContents passed!");
        } else {
            System.out.println("testCartContents failed!");
        }
    }
}
