package com.lihg.library.tabbarlayout;

import android.support.v4.app.Fragment;

public class YTabbarItem {

    private int imageResId;
    private int imageSelectedResId;
    private int textResId;
    private String text;
    private Fragment fragment;

    private YTabbarItem(int imageResId, int imageSelectedResId, int textResId, String text, Fragment fragment) {
        this.imageResId = imageResId;
        this.imageSelectedResId = imageSelectedResId;
        this.textResId = textResId;
        this.text = text;
        this.fragment = fragment;
    }

    public static YTabbarItem createItem(int imageResId, int imageSelectedResId, int textResId, Fragment fragment) {
        return new YTabbarItem(imageResId, imageSelectedResId, textResId, null, fragment);
    }

    public static YTabbarItem createItem(int imageResId, int imageSelectedResId, String text,  Fragment fragment) {
        return new YTabbarItem(imageResId, imageSelectedResId, 0, text, fragment);
    }

    public static YTabbarItem createCenterItem(int imageResId) {
        return new YTabbarItem(imageResId, 0, 0, null, null);
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getImageSelectedResId() {
        return imageSelectedResId;
    }

    public int getTextResId() {
        return textResId;
    }

    public String getText() {
        return text;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
