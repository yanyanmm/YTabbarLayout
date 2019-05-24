package com.lihg.tabbarlayout;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lihg.library.tabbarlayout.YTabbarItem;
import com.lihg.library.tabbarlayout.YTabbarLayout;
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
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_home, R.mipmap.menu_home, "首页",null));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_display, R.mipmap.menu_display, "展示",null));
        tabbarItems.add(YTabbarItem.createCenterItem(R.mipmap.qing));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_student, R.mipmap.menu_student, "查询",null));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_teacher, R.mipmap.menu_teacher, "我的",null));
        tabbarLayout.setTabbarItems(tabbarItems, getSupportFragmentManager());
    }
}
