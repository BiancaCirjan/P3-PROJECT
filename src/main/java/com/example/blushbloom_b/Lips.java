package com.example.blushbloom_b;
public class Lips extends MakeupProduct {
    private String shade;
    private String finish;

    public Lips(String name, String brand, double price, int stock, String shade, String finish) {
        super(name, brand, price, stock);
        this.shade = shade;
        this.finish = finish;
    }

    public String getShade() {
        return shade;
    }

    public String getFinish() {
        return finish;
    }

    @Override
    public String getDetails() {
        return getName() + " - " + getBrand() + " | Shade: " + shade + ", Finish: " + finish;
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
        return false;
    }
}
