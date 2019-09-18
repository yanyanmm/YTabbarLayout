package com.lihg.tabbarlayout;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lihg.library.tabbarlayout.YBadgeView;
import com.lihg.library.tabbarlayout.YTabbarItem;
import com.lihg.library.tabbarlayout.YTabbarLayout;
import com.lihg.tabbarlayout.fragment.CircleFragment;
import com.lihg.tabbarlayout.fragment.HomeFragment;
import com.lihg.tabbarlayout.fragment.MsgFragment;
import com.lihg.tabbarlayout.fragment.MyFragment;
import com.ycl.tabview.library.TabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
        setContentView(R.layout.activity_main);

        //TabbarLayout
        YTabbarLayout tabbarLayout = findViewById(R.id.tabbarLayout);
        List<YTabbarItem> tabbarItems = new ArrayList<YTabbarItem>();
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_home, R.mipmap.menu_home_selected, "首页", new HomeFragment()));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_circle, R.mipmap.menu_circle_selected, "班级圈",new CircleFragment()));
        //tabbarItems.add(YTabbarItem.createCenterItem(R.mipmap.menu_center));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_msg, R.mipmap.menu_msg_selected, "消息",new MsgFragment()));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_my, R.mipmap.menu_my_selected, "我的",new MyFragment()));
        //tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_home_selected, "首页", new HomeFragment()));
        //tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_circle_selected, "班级圈",new CircleFragment()));
        //tabbarItems.add(YTabbarItem.createCenterItem(R.mipmap.menu_center));
        //tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_msg_selected, "消息",new MsgFragment()));
        //tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_my_selected, "我的",new MyFragment()));
        tabbarLayout.setTabbarItems(tabbarItems, getSupportFragmentManager());

        final YBadgeView badgeView0 = new YBadgeView(this);
        badgeView0.setTargetView(tabbarLayout.getTabbarItemViews().get(0).getImageView());

        final YBadgeView badgeView1 = new YBadgeView(this);
        badgeView1.setTargetView(tabbarLayout.getTabbarItemViews().get(1).getImageView());
        badgeView1.setBadgeCount(8);

        final YBadgeView badgeView2 = new YBadgeView(this);
        badgeView2.setTargetView(tabbarLayout.getTabbarItemViews().get(2).getImageView());
        badgeView2.setBadgeCount(105);

        final YBadgeView badgeView3 = new YBadgeView(this);
        badgeView3.setTargetView(tabbarLayout.getTabbarItemViews().get(3).getImageView());
        badgeView3.setBadgeText("NEW");

        tabbarLayout.setOnTabbarItemClickListener(new YTabbarLayout.OnTabbarItemClickListener() {
            @Override
            public void onItemChanged(int fromIndex, int toIndex) {
                if (fromIndex != toIndex) {
                    switch (toIndex) {
                        case 0: {
                            badgeView0.hide(false);
                        }
                        break;
                        case 1: {
                            badgeView1.hide();
                        }
                        break;
                        case 2: {
                            badgeView2.hide();
                        }
                        break;
                        case 3: {
                            badgeView3.hide();
                        }
                        break;
                        default:
                    }
                }
            }

            @Override
            public void onClickCenterItem(View v) {
                Toast.makeText(getApplicationContext(), "点击中心按钮", Toast.LENGTH_LONG).show();
            }
        });
    }
}
