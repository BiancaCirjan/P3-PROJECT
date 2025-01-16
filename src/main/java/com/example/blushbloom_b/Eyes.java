package com.example.blushbloom_b;
public class Eyes extends MakeupProduct {
    private String color;
    private boolean waterproof;

    public Eyes(String name, String brand, double price, int stock, String color, boolean waterproof) {
        super(name, brand, price, stock);
        this.color = color;
        this.waterproof = waterproof;
    }

    public String getColor() {
        return color;
    }

    public boolean isWaterproof() {
        return false;
    }

    @Override
    public String getDetails() {
        return getName() + " - " + getBrand() + " | Color: " + color + ", Waterproof: " + waterproof;
    }

    @Override
    public void like() {
        setLiked(true);
    }

    @Override
    public void unlike() {
        setLiked(false);
    }

    private void setLiked(boolean b) {
    }

    @Override
    public boolean isLiked() {
        return isLiked();
    }
}
