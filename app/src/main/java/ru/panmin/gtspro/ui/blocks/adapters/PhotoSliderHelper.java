package ru.panmin.gtspro.ui.blocks.adapters;

import android.support.annotation.DrawableRes;

public class PhotoSliderHelper {
    private String comment;

    //optional @DrawableRes
    @DrawableRes
    private int resId;

    public PhotoSliderHelper(String comment, int url) {
        this.comment = comment;
        this.resId = url;
    }

    @Override
    public String toString() {
        return comment;
    }

    //getters and setters


    public int getResId() {
        return resId;
    }
}
