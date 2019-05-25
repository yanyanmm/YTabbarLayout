package com.lihg.library.tabbarlayout;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YTabbarItemView extends LinearLayout {

    private YTabbarItem mTabbarItem;
    private YTabbarItemAttr mTabbarItemAttr;

    private ImageView mImageView;
    private TextView mTextView;

    public YTabbarItemView(Context context) {
        super(context);
        this.setOrientation(VERTICAL);
        this.setGravity(Gravity.CENTER);
        this.setClickable(true);

        mImageView = new ImageView(context);
        mImageView.setLayoutParams(new LayoutParams(-2, -2));
        this.addView(mImageView);

        mTextView = new TextView(context);
        mTextView.setLayoutParams(new LayoutParams(-2, -2));
        this.addView(mTextView);
    }

    public void setTabbarItem(YTabbarItem tabbarItem, YTabbarItemAttr tabbarItemAttr) {
        mTabbarItem = tabbarItem;
        mTabbarItemAttr = tabbarItemAttr;
        mImageView.setImageResource(tabbarItem.getImageResId());
        mTextView.setVisibility(mTabbarItem.center() ? View.GONE : VISIBLE);
        if (!mTabbarItem.center()) {
            LayoutParams params = new LayoutParams(-2, -2);
            params.topMargin = mTabbarItemAttr.getTextMarginTop();
            mTextView.setLayoutParams(params);
            mTextView.setTextColor(mTabbarItemAttr.getTextColor());
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTabbarItemAttr.getTextSize());
            if (tabbarItem.getTextResId() != 0) {
                mTextView.setText(tabbarItem.getTextResId());
            }
            if (tabbarItem.getText() != null) {
                mTextView.setText(tabbarItem.getText());
            }
        }
    }

    public void setSelected(boolean selected) {
        if (mTabbarItemAttr.getSelectedStyle() == 0) {
            this.setBackgroundColor(selected ? mTabbarItemAttr.getSelectedBackgroundColor() : Color.TRANSPARENT);
        } else {
            mImageView.setImageResource(selected ? mTabbarItem.getImageSelectedResId() : mTabbarItem.getImageResId());
            mTextView.setTextColor(selected ? mTabbarItemAttr.getSelectedTextColor() : mTabbarItemAttr.getTextColor());
        }
    }

    public YTabbarItem getTabbarItem() {
        return mTabbarItem;
    }

    public YTabbarItemAttr getTabbarItemAttr() {
        return mTabbarItemAttr;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }
}
