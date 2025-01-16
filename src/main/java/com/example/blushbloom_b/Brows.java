package com.example.blushbloom_b;
public class Brows extends MakeupProduct {
    private String color;
    private String type;

    public Brows(String name, String brand, double price, int stock, String color, String type) {
        super(name, brand, price, stock);
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDetails() {
        return getName() + " - " + getBrand() + " | Color: " + color + ", Type: " + type;
    }

    @Override
    public void like() {
        setLiked(true);
    }

    private void setLiked(boolean b) {
    }

    @Override
    public void unlike() {
        setLiked(false);
    }

    @Override
    public boolean isLiked() {
        return isLiked();
    }
}
