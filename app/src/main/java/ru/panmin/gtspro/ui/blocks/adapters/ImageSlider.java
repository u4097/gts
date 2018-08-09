package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.DrawableRes;

public class ImageSlider {
    private String name;

    //optional @DrawableRes
    @DrawableRes
    private int resId;

    public ImageSlider(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return name;
    }

    //getters and setters


    public int getResId() {
        return resId;
    }
}
