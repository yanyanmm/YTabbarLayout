package com.lihg.library.tabbarlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class YTabbarLayout extends LinearLayout {

    private Context mContext;

    private int mTabbarCurrentIndex;
    private int mTabbarHeight;
    private int mTabbarBackgroundColor;

    private YTabbarItemAttr mTabbarItemAttr;

    private OnTabbarItemClickListener mOnTabbarItemClickListener;

    private FrameLayout mFragmentLayout;
    private LinearLayout mTabbarView;

    private FragmentManager mFragmentManager;

    private List<YTabbarItemView> mTabbarItemViews;

    public YTabbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YTabbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context.getApplicationContext();
        mTabbarCurrentIndex = 0;
        mTabbarHeight = YDesityUtil.dp2px(mContext, 50);
        mTabbarBackgroundColor = Color.WHITE;

        mTabbarItemAttr = new YTabbarItemAttr();
        mTabbarItemAttr.setTextSize(YDesityUtil.sp2px(mContext, 10));
        mTabbarItemAttr.setTextMarginTop(YDesityUtil.dp2px(mContext, 3));

        mTabbarItemViews = new ArrayList<YTabbarItemView>();

        this.setOrientation(VERTICAL);
        this.initAttrs(attrs);
        this.initViews();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.YTabbarLayout);
        for(int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.YTabbarLayout_tabbar_backgroundColor) {
                mTabbarBackgroundColor = typedArray.getColor(attr, mTabbarBackgroundColor);
            } else if (attr == R.styleable.YTabbarLayout_tabbar_selected_backgroundColor) {
                mTabbarItemAttr.setSelectedBackgroundColor(typedArray.getColor(attr, mTabbarItemAttr.getSelectedBackgroundColor()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_textColor) {
                mTabbarItemAttr.setTextColor(typedArray.getColor(attr, mTabbarItemAttr.getTextColor()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_selected_textColor) {
                mTabbarItemAttr.setSelectedTextColor(typedArray.getColor(attr, mTabbarItemAttr.getSelectedTextColor()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_height) {
                mTabbarHeight = typedArray.getDimensionPixelSize(attr, mTabbarHeight);
            } else if (attr == R.styleable.YTabbarLayout_tabbar_textSize) {
                mTabbarItemAttr.setTextSize(typedArray.getDimensionPixelSize(attr, mTabbarItemAttr.getTextSize()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_textMarginTop) {
                mTabbarItemAttr.setTextMarginTop(typedArray.getDimensionPixelSize(attr, mTabbarItemAttr.getTextMarginTop()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_imageWidth) {
                mTabbarItemAttr.setImageWidth(typedArray.getDimensionPixelSize(attr, mTabbarItemAttr.getImageWidth()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_imageHeight) {
                mTabbarItemAttr.setImageHeight(typedArray.getDimensionPixelSize(attr, mTabbarItemAttr.getImageHeight()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_center_imageWidth) {
                mTabbarItemAttr.setCenterImageWidth(typedArray.getDimensionPixelSize(attr, mTabbarItemAttr.getCenterImageWidth()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_center_imageHeight) {
                mTabbarItemAttr.setCenterImageHeight(typedArray.getDimensionPixelSize(attr, mTabbarItemAttr.getCenterImageHeight()));
            } else if (attr == R.styleable.YTabbarLayout_tabbar_currentIndex) {
                mTabbarCurrentIndex = typedArray.getInteger(attr, mTabbarCurrentIndex);
            } else if (attr == R.styleable.YTabbarLayout_tabbar_selected_style) {
                mTabbarItemAttr.setSelectedStyle(typedArray.getInt(attr, mTabbarItemAttr.getSelectedStyle()));
            }
        }
        typedArray.recycle();
    }

    private void initViews() {
        mFragmentLayout = new FrameLayout(mContext);
        mFragmentLayout.setId(R.id.tabbar_fragment_container);
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
        YTabbarItemView tabbarItemView = new YTabbarItemView(mContext);
        tabbarItemView.setLayoutParams(new LayoutParams(0, -1, 1));
        tabbarItemView.setTabbarItem(tabbarItem, mTabbarItemAttr);
        mTabbarView.addView(tabbarItemView);
        if (tabbarItem.center()) {
            tabbarItemView.setOnClickListener(mTabbarCenterItemOnClickListener);
        } else {
            tabbarItemView.setTag(mTabbarItemViews.size());
            tabbarItemView.setOnClickListener(mTabbarItemOnClickListener);
            mTabbarItemViews.add(tabbarItemView);
        }
    }

    private void setTabbarItemSelected(int index, boolean selected) {
        if (index < mTabbarItemViews.size()) {
            YTabbarItemView tabbarItemView = mTabbarItemViews.get(index);
            tabbarItemView.setSelected(selected);

            Fragment fragment = tabbarItemView.getTabbarItem().getFragment();
            if (fragment != null) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                if (selected) {
                    if (fragment.isAdded()) {
                        transaction.show(fragment).commit();
                    } else {
                        transaction.add(R.id.tabbar_fragment_container, fragment).commit();
                    }
                } else {
                    if (fragment.isAdded()) {
                        transaction.hide(fragment).commit();
                    }
                }

            }
        }
    }

    private View.OnClickListener mTabbarItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index = (int)v.getTag();
            if (index != mTabbarCurrentIndex) {
                setTabbarItemSelected(index, true);
                setTabbarItemSelected(mTabbarCurrentIndex, false);
                mTabbarCurrentIndex = index;
                if (mOnTabbarItemClickListener != null) {
                    mOnTabbarItemClickListener.onClickItem(v);
                }
            }
        }
    };

    private View.OnClickListener mTabbarCenterItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnTabbarItemClickListener != null) {
                mOnTabbarItemClickListener.onClickCenterItem(v);
            }
        }
    };

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
            if (mTabbarCurrentIndex >= tabbarItems.size()) {
                mTabbarCurrentIndex = 0;
            }
            this.setTabbarItemSelected(mTabbarCurrentIndex, true);
        }
    }

    public int getTabbarHeight() {
        return mTabbarHeight;
    }

    public int getTabbarBackgroundColor() {
        return mTabbarBackgroundColor;
    }

    public List<YTabbarItemView> getTabbarItemViews() {
        return mTabbarItemViews;
    }

    public void setOnTabbarItemClickListener(OnTabbarItemClickListener listener) {
        this.mOnTabbarItemClickListener = listener;
    }

    public interface OnTabbarItemClickListener {
        void onClickItem(View v);
        void onClickCenterItem(View v);
    }
}
