package com.lihg.library.tabbarlayout;

import android.graphics.Color;

public class YTabbarItemAttr {

    private int selectedStyle;
    private int selectedBackgroundColor;
    private int textSize;
    private int textMarginTop;
    private int textColor;
    private int selectedTextColor;
    private int imageWidth;
    private int imageHeight;
    private int centerImageWidth;
    private int centerImageHeight;

    public YTabbarItemAttr() {
        this.selectedStyle = 1;
        this.selectedBackgroundColor = Color.rgb(252, 202, 49);
        this.textSize = 10;
        this.textMarginTop = 3;
        this.textColor = Color.BLACK;
        this.selectedTextColor = Color.RED;
        this.imageWidth = -2;
        this.imageHeight = -2;
        this.centerImageWidth = -2;
        this.centerImageHeight = -2;
    }

    public int getSelectedStyle() {
        return selectedStyle;
    }

    public void setSelectedStyle(int selectedStyle) {
        this.selectedStyle = selectedStyle;
    }

    public int getSelectedBackgroundColor() {
        return selectedBackgroundColor;
    }

    public void setSelectedBackgroundColor(int selectedBackgroundColor) {
        this.selectedBackgroundColor = selectedBackgroundColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextMarginTop() {
        return textMarginTop;
    }

    public void setTextMarginTop(int textMarginTop) {
        this.textMarginTop = textMarginTop;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getCenterImageWidth() {
        return centerImageWidth;
    }

    public void setCenterImageWidth(int centerImageWidth) {
        this.centerImageWidth = centerImageWidth;
    }

    public int getCenterImageHeight() {
        return centerImageHeight;
    }

    public void setCenterImageHeight(int centerImageHeight) {
        this.centerImageHeight = centerImageHeight;
    }
}
