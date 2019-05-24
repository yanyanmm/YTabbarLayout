package com.lihg.library.tabbarlayout;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class YTabbarLayout extends LinearLayout {

    private Context mContext;

    private int mTabbarHeight;
    private int mTabbarBackgroundColor;

    private float mTabbarItemTextSize;
    private int mTabbarItemTextColor;
    private int mTabbarItemSelectedTextColor;

    private int mTabbarItemTextAndImagePadding;

    private FrameLayout mFragmentLayout;
    private LinearLayout mTabbarView;

    private FragmentManager mFragmentManager;

    public YTabbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YTabbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context.getApplicationContext();
        mTabbarHeight = YDesityUtil.dp2px(mContext, 50);
        mTabbarBackgroundColor = Color.WHITE;
        mTabbarItemTextSize = 10.0f;
        mTabbarItemTextColor = Color.BLACK;
        mTabbarItemSelectedTextColor = Color.RED;
        mTabbarItemTextAndImagePadding = YDesityUtil.dp2px(mContext, 6);
        this.setOrientation(VERTICAL);
        this.initAttrs(mContext);
        this.initView();
    }

    private void initAttrs(Context context) {


    }

    private void initView() {
        mFragmentLayout = new FrameLayout(mContext);
        mFragmentLayout.setLayoutParams(new LayoutParams(-1, -2, 1));
        this.addView(mFragmentLayout);

        View lineView = new View(mContext);
        lineView.setLayoutParams(new LayoutParams(-1, 1));
        lineView.setBackgroundColor(Color.LTGRAY);
        this.addView(lineView);

        mTabbarView = new LinearLayout(mContext);
        mTabbarView.setLayoutParams(new LayoutParams(-1, mTabbarHeight));
        mTabbarView.setBackgroundColor(mTabbarBackgroundColor);
        this.addView(mTabbarView);
    }

    private void addTabberItem(YTabbarItem tabbarItem) {
        LinearLayout tabbarItemView = new LinearLayout(mContext);
        tabbarItemView.setLayoutParams(new LayoutParams(0, -1, 1));
        tabbarItemView.setOrientation(VERTICAL);
        tabbarItemView.setGravity(Gravity.CENTER);
        mTabbarView.addView(tabbarItemView);

        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new LayoutParams(-2, -2));
        imageView.setImageResource(tabbarItem.getImageResId());
        tabbarItemView.addView(imageView);

        if (tabbarItem.getTextResId() != 0 || tabbarItem.getText() != null) {
            TextView textView = new TextView(mContext);
            LayoutParams params = new LayoutParams(-2, -2);
            params.topMargin = mTabbarItemTextAndImagePadding;
            textView.setLayoutParams(params);
            textView.setTextColor(mTabbarItemTextColor);
            textView.setTextSize(mTabbarItemTextSize);
            if (tabbarItem.getTextResId() != 0) {
                textView.setText(tabbarItem.getTextResId());
            }
            if (tabbarItem.getText() != null) {
                textView.setText(tabbarItem.getText());
            }
            tabbarItemView.addView(textView);
        }
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(VERTICAL);
    }

    /**
     * 设置菜单项
     * @param tabbarItems
     * @param fragmentManager
     */
    public void setTabbarItems(List<YTabbarItem> tabbarItems, FragmentManager fragmentManager) {
        mTabbarView.removeAllViews();
        mFragmentManager = fragmentManager;

        if (tabbarItems != null) {
            for (int i = 0; i < tabbarItems.size(); i++) {
                this.addTabberItem(tabbarItems.get(i));
            }
        }
        /*if (this.mTabViewDefaultPosition >= tabViewChildList.size()) {
            this.index = 0;
            this.currentTabIndex = 0;
            this.mTabViewDefaultPosition = 0;
        }//*/
    }

    public int getTabbarHeight() {
        return mTabbarHeight;
    }

    public int getTabbarBackgroundColor() {
        return mTabbarBackgroundColor;
    }
}
