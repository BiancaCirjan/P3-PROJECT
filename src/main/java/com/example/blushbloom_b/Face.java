package com.example.blushbloom_b;
public class Face extends MakeupProduct {
    private String skinType;
    private String coverage;

    public Face(String name, String brand, double price, int stock, String skinType, String coverage) {
        super(name, brand, price, stock);
        this.skinType = skinType;
        this.coverage = coverage;
    }

    public String getSkinType() {
        return null;
    }

    public String getCoverage() {
        return coverage;
    }

    @Override
    public String getDetails() {
        return getName() + " - " + getBrand() + " | Coverage: " + coverage + ", Skin Type: " + skinType;
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
