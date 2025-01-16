package com.example.blushbloom_b;

public class ProductTest {
    public static void main(String[] args) {
        testProductCreation();
        testProductStockUpdate();
    }

    public static void testProductCreation() {
        Product product = new Product(1, "Lipstick", 19.99, 50);

        if (product.getId() == 1 &&
                product.getName().equals("Lipstick") &&
                product.getPrice() == 19.99 &&
                product.getStock() == 50) {
            System.out.println("testProductCreation passed!");
        } else {
            System.out.println("testProductCreation failed!");
        }
    }


    public static void testProductStockUpdate() {
        Product product = new Product(1, "Lipstick", 19.99, 50);
        product.setStock(30);

        if (product.getStock() == 30) {
            System.out.println("testProductStockUpdate passed!");
        } else {
            System.out.println("testProductStockUpdate failed!");
        }
    }
}
