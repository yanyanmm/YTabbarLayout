# YTabbarLayout
底部菜单导航


![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot1.png)
![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot2.png)
![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot3.png)
![Alt text](https://github.com/yanyanmm/YTabbarLayout/blob/master/shot/tabbar_shot4.png)

# 1. 在project的build.gradle添加如下代码(如下图)
```Java
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
# 2. 在build.gradle添加依赖
```Java
implementation 'com.github.yanyanmm:YTabbarLayout:v1.0.2'
```
# 3. 在xml中,加入以下代码:
```xml
  <com.lihg.library.tabbarlayout.YTabbarLayout
        android:id="@+id/tabbarLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
# 4. 在代码中添加如下代码:
```Java
YTabbarLayout tabbarLayout = findViewById(R.id.tabbarLayout);
List<YTabbarItem> tabbarItems = new ArrayList<YTabbarItem>();
tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_home, R.mipmap.menu_home_selected, "首页",new HomeFragment()));
tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_circle, R.mipmap.menu_circle_selected, "班级圈",new CircleFragment()));
tabbarItems.add(YTabbarItem.createCenterItem(R.mipmap.menu_center));
tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_msg, R.mipmap.menu_msg_selected, "消息",new MsgFragment()));
tabbarItems.add(YTabbarItem.createItem(R.mipmap.menu_my, R.mipmap.menu_my_selected, "我的",new MyFragment()));
tabbarLayout.setTabbarItems(tabbarItems, getSupportFragmentManager());  
```
# 5. 自定义属性设置:
```xml 
<declare-styleable name="YTabbarLayout">
    <attr format="reference|color" name="tabbar_backgroundColor"/>
    <attr format="reference|color" name="tabbar_selected_backgroundColor"/>
    <attr format="reference|color" name="tabbar_textColor"/>
    <attr format="reference|color" name="tabbar_selected_textColor"/>
    <attr format="dimension" name="tabbar_height"/>
    <attr format="dimension" name="tabbar_textSize"/>
    <attr format="dimension" name="tabbar_textMarginTop"/>
    <attr format="dimension" name="tabbar_imageWidth"/>
    <attr format="dimension" name="tabbar_imageHeight"/>
    <attr format="dimension" name="tabbar_center_imageWidth"/>
    <attr format="dimension" name="tabbar_center_imageHeight"/>
    <attr format="integer" name="tabbar_currentIndex"/>
    <attr name="tabbar_selected_style">
        <flag name="background" value="0"/>
        <flag name="image_text" value="1"/>
    </attr>
</declare-styleable>
``` 
# 6. BadgeView设置:
```Java
YBadgeView badgeView0 = new YBadgeView(this);
badgeView0.setTargetView(tabbarLayout.getTabbarItemViews().get(0).getImageView());

YBadgeView badgeView1 = new YBadgeView(this);
badgeView1.setTargetView(tabbarLayout.getTabbarItemViews().get(1).getImageView());
badgeView1.setBadgeCount(8);

YBadgeView badgeView2 = new YBadgeView(this);
badgeView2.setTargetView(tabbarLayout.getTabbarItemViews().get(2).getImageView());
badgeView2.setBadgeCount(105);

YBadgeView badgeView3 = new YBadgeView(this);
badgeView3.setTargetView(tabbarLayout.getTabbarItemViews().get(3).getImageView());
badgeView3.setBadgeText("NEW");
```
