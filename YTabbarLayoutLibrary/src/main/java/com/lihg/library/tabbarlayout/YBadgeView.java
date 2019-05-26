package com.lihg.library.tabbarlayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

public class YBadgeView extends AppCompatTextView {

    private int mBadgeCount = 0;

    public YBadgeView(Context context) {
        super(context);

        this.setTextColor(Color.WHITE);
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        this.setGravity(Gravity.CENTER);
        this.setPadding(dp2px(4), dp2px(0), dp2px(4), dp2px(0));
        this.setBadgeBackgroundColor(Color.RED, 8, true);

        this.setBadgePoint();
    }

    public void setBadgeBackgroundColor(int color, int radius, boolean isDp) {
        int r = isDp ? dp2px(radius) : radius;
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{ r, r, r, r, r, r, r, r }, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(color);
        this.setBackground(shapeDrawable);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (text == null) {
            mBadgeCount = 0;
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
    }

    public void setBadgePoint() {
        this.setBadgePoint(this.dp2px(8));
    }

    public void setBadgePoint(int size) {
        this.setText("");
        this.setBadgeLayoutParams(size, size);
    }

    public void setBadgeCount(int count) {
        if (count <= 0) {
            this.setText(null);
        } else {
            mBadgeCount = count;
            this.setBadgeText(count > 99 ? "99+" : String.valueOf(count));
        }
    }

    public int getBadgeCount() {
        return mBadgeCount;
    }

    public void setBadgeText(String text) {
        this.setText(text);
        this.setBadgeLayoutParams();
    }

    public void setTargetView(View target) {
        if (target == null) {
            return;
        }
        if (this.getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);
        } else if (target.getParent() instanceof ViewGroup) {
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int index = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);

            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();

            badgeContainer.setLayoutParams(parentLayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, index, parentLayoutParams);
            badgeContainer.addView(target);
            badgeContainer.addView(this);
        }
    }

    public void hide() {
        this.hide(true);
    }

    public void hide(boolean anim) {
        if (this.getVisibility() != VISIBLE) {
            return;
        }
        if (anim) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.1f);
            alphaAnimation.setDuration(300);
            ScaleAnimation scaleAnimation = new ScaleAnimation(1,0.5f,1,0.5f
                    , Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            scaleAnimation.setDuration(300);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(scaleAnimation);
            this.startAnimation(animationSet);
            animationSet.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    setText(null);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        } else {
            this.setText(null);
        }
    }

    public int getTextWidth() {
        Paint paint = new Paint();
        paint.setTextSize(getTextSize());
        return (int)paint.measureText(getText().toString()) + getPaddingStart() + getPaddingEnd();
    }

    private void setBadgeLayoutParams() {
        this.setBadgeLayoutParams(-2, dp2px(14));
    }

    private void setBadgeLayoutParams(int width, int height) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        if (height > 0) {
            params.rightMargin = height / 2 - (width > 0 ? width : this.getTextWidth());
        }
        this.setLayoutParams(params);
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(1, dp, getContext().getResources().getDisplayMetrics());
    }

}
