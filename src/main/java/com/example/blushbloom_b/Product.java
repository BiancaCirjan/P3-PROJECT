package com.example.blushbloom_b;

public class Product {
    private int id;
    private String name;
    private String imagePath;
    private double price;
    private int stock;


    public Product(int id, String name, String imagePath, double price, int stock) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0");
        }
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.stock = stock;
    }



    public Product(String name) {
        this.name = name;
        this.imagePath = null;
        this.price = 0.0;
        this.stock = 0;
    }

    public Product(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = 0.0;
        this.stock = 0;
    }

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.imagePath = null;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public static void main(String[] args) {
        Product testProduct = new Product(1, "Test Product", 19.99, 10);
        System.out.println(testProduct);

        Product nameOnlyProduct = new Product("Name Only");
        System.out.println(nameOnlyProduct);

        Product fullProduct = new Product(2, "Full Product", "image/path", 25.99, 5);
        System.out.println(fullProduct);
    }


}
