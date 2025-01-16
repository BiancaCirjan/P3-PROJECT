package com.example.blushbloom_b;
public class MakeupBrushes extends MakeupProduct {
    private String type;
    private String material;

    public MakeupBrushes(String name, String brand, double price, int stock, String type, String material) {
        super(name, brand, price, stock);
        this.type = type;
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String getDetails() {
        return getName() + " - " + getBrand() + " | Type: " + type + ", Material: " + material;
    }

    @Override
    public void like() {

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
