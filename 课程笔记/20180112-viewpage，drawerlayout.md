##1. 创建app_bar_main.xml布局文件
在res/layout中
将activity_main这个布局中CoordinatorLayout内容剪切到app_bar_main.xml中
在头部增加下面三句话
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.itfollowme.zhihu.ui.activity.MainActivity">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/BaseAppTheme.AppBarOverlay">
        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/BaseAppTheme.PopupOverlay"/>
    </android.support.design.widget.AppBarLayout>
    </android.support.design.widget.CoordinatorLayout>

##2. 创建content_main.xml布局
在app_bar_main.xml中插入到appbarlayout下面
 content_main.xml的头部
              xmlns:app="http://schemas.android.com/apk/res-auto"
              xmlns:tools="http://schemas.android.com/tools"

               app:layout_behavior="@string/appbar_scrolling_view_behavior"
              tools:context="com.itfollowme.zhihu.ui.activity.MainActivity"
              tools:showIn="@layout/app_bar_main"

 在MainActivity的onCreate方法里面加入
     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

##3. 创建菜单
  右击->Menu resource file
  menu_main.xml
   <item android:id="@+id/action_notification"
        android:title="@string/action_notification"
          android:orderInCategory="100"
           android:icon="@drawable/ic_message"
          app:showAsAction="ifRoom"
    />

##4. 创建抽屉头部
在布局文件中创建一个nav_header_main.xml
先实现蓝色的渐变效果
    点击drawable目录，new->drawable resource file
    side_nav_bar.xml

    <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
        <gradient
            android:angle="135"
            android:centerColor="@color/colorPrimary"
            android:endColor="#0277bd"
            android:startColor="#4fc3f7"
            android:type="linear"/>
    </shape>
    设置头部的背景:
    nav_header_main.xml中，对根元素增加background属性值side_nav_bar
    设置头部的高度
    android:layout_height="192dp"

实现圆形头像
    使用第三方的控件
    在dependency中加入
    de.hdodenhof:circleimageview:2.2.0
    在nav_header_main.xml中加入圆形头像
    <de.hdodenhof.circleimageview.CircleImageView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/profile_image"
    android:layout_width="96dp"
    android:layout_height="96dp"
    android:src="@drawable/profile"
    app:civ_border_width="2dp"
    app:civ_border_color="#FF000000"/>

做菜单
  在menu中右击新建一个菜单布局文件activity_main_drawer.xml
  菜单的字体颜色需要到NavigationView中加
  app:itemTextColor="@color/colorPrimaryDark"

做Toolbar上的效果
  在activity_main.xml中给DrawerLayout添加id  drawer_layout
  在MainActivity中加入
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

在MainActivity中重写
   @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

##5. 做标题栏
###a. 在app_bar_main.xml加入TabLayout
        <android.support.design.widget.TabLayout
            android:id="@+id/tab_zhihu_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@color/colorPrimary">
        </android.support.design.widget.TabLayout>
###b. 在content_main.xml中加入ViewPager
   <android.support.v4.view.ViewPager
        android:id="@+id/vp_zhihu_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"  />

###c. 在MainActivity中加入对TabLayout和ViewPager的引用
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

        mTabLayout = findViewById(R.id.tab_zhihu_main);
        mViewPager = findViewById(R.id.vp_zhihu_main);

###d. 增加标题栏内容
    在MainActivity中增加
    String[] tabTitle = new String[]{"日报","主题","专栏","热门"};

###e. 增加Fragment
    在fragment包下，新建几个Fragment，有几个标题你就建几个

###f. 在adapter包下创建ZhihuMainAdapter类
    public class ZhihuMainAdapter extends FragmentPagerAdapter

    重写里面的抽象方法
    增加构造函数
    private List<Fragment> fragments;

    public ZhihuMainAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


                app:tabTextAppearance="@android:style/TextAppearance.Material.Subhead"
            app:tabIndicatorColor="@android:color/white"
            app:tabSelectedTextColor="@android:color/white"
            app:tabTextColor="@android:color/white"
            app:tabMode="fixed"
            app:tabGravity="fill"