package com.example.blushbloom_b;

public abstract class MakeupProduct implements Likeable, Discountable {
    private String name;
    private String brand;
    private double price;
    private int stock;
    private boolean liked;
    private boolean isDiscounted;

    public MakeupProduct(String name, String brand, double price, int stock) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.liked = false;
        this.isDiscounted = false;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isDiscounted() { return isDiscounted; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    public void reduceStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Not enough stock available.");
        }
    }

    public abstract String getDetails();

    @Override
    public double applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        double discountAmount = price * (discountPercentage / 100);
        double discountedPrice = price - discountAmount;

        saveDiscountToDatabase(discountPercentage, discountedPrice);

        isDiscounted = true;
        return discountedPrice;
    }

    private void saveDiscountToDatabase(double discountPercentage, double discountedPrice) {
        String query = "INSERT INTO DiscountedProducts (product_id, discount_percentage, discounted_price) " +
                "VALUES ((SELECT product_id FROM MakeupProducts WHERE name = ?), ?, ?)";


    }
}
