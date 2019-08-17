package com.example.bottomtesttwo.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.Fragment1;
import com.example.bottomtesttwo.fragments.Fragment2;
import com.example.bottomtesttwo.fragments.Fragment3;
import com.example.bottomtesttwo.fragments.Fragment4;
import com.example.bottomtesttwo.util.StatusBar.StatusBarUtil;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;//用于接收底部菜单栏实体
    private MenuItem mMenuItem;//用于获取菜单栏当前处于哪一个位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();
        //顶部状态栏设置属性（具体细节见MainActivity中的静态实现方法）
        //isPadding-是否预留出状态栏高度:true=是、false=fou;
        //dark:true=黑色字体  false=白色
        setStatusBar(this,false, true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Fragment1());//默认加载fragment1页面

        //底部菜单栏实体
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv);

        //底部菜单栏点击监听事件
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mMenuItem = menuItem;
                switch (menuItem.getItemId()) {

                    case R.id.item_tab1://底部导航栏卡包点击事件相应
                        replaceFragment(new Fragment1());
                        setStatusBar(MainActivity.this,false, true);
                        return true;

                    case R.id.item_tab2://底部导航栏存钱点击事件相应
                        replaceFragment(new Fragment2());
                        setStatusBar(MainActivity.this,false, true);
                        return true;

                    case R.id.item_tab3://底部导航栏记账点击事件相应
                        replaceFragment(new Fragment3());
                        setStatusBar(MainActivity.this,false, false);
                        return true;

                    case R.id.item_tab4://底部导航栏我点击事件相应
                        replaceFragment(new Fragment4());
                        setStatusBar(MainActivity.this,false, true);
                        return true;
                }
                return false;
            }
        });

    }

    //切换fragment
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.bolck_fragmelayout,fragment);
        transaction.commit();
    }

    //设置顶部状态栏设置属性（相关类在util包下的StatusBar包中）
    //activity为操作活动对象；
    // isPadding为是否预留出状态栏高度的padding：true为是，false为否；(因为这个预留并不精确，所以不是很建议使用)
    // dark为字体颜色：true=黑色字体  false=白色
    public static void setStatusBar(Activity activity, boolean isPadding, boolean dark){
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(activity,isPadding);
        //设置状态栏透明（此函数默认状态栏透明）
        StatusBarUtil.setTranslucentStatus(activity);
        //true=黑色字体  false=白色
        StatusBarUtil.setStatusBarDarkTheme(activity, dark);
    }



}