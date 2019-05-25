package com.lihg.tabbarlayout;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lihg.library.tabbarlayout.YTabbarItem;
import com.lihg.library.tabbarlayout.YTabbarLayout;
import com.lihg.tabbarlayout.fragment.HomeFragment;
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
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_home, R.mipmap.menu_home_selected, "首页",new HomeFragment()));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_circle, R.mipmap.menu_circle_selected, "班级圈",null));
        tabbarItems.add(YTabbarItem.createCenterItem(R.mipmap.ic_launcher));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_msg, R.mipmap.menu_msg_selected, "消息",null));
        tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_my, R.mipmap.menu_my_selected, "我的",null));
        tabbarLayout.setTabbarItems(tabbarItems, getSupportFragmentManager());
        tabbarLayout.setOnTabbarItemClickListener(new YTabbarLayout.OnTabbarItemClickListener() {
            @Override
            public void onClickItem(View v) {

            }

            @Override
            public void onClickCenterItem(View v) {
                Toast.makeText(getApplicationContext(), "点击中心按钮", Toast.LENGTH_LONG).show();
            }
        });
    }
}
