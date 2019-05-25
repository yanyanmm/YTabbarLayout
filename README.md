# YTabbarLayout
底部菜单导航


![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot1.png)
![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot2.png)
![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot3.png)



```Java
implementation 'com.github.yanyanmm:YTabbarLayout:v1.0.1'
```

```Java
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
        
```
