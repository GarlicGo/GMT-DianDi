package com.example.bottomtesttwo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.bottomtesttwo.fragments.Fragment1;
import com.example.bottomtesttwo.fragments.Fragment2;
import com.example.bottomtesttwo.fragments.Fragment3;
import com.example.bottomtesttwo.fragments.Fragment4;
import com.example.bottomtesttwo.util.StatusBar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mPagerAdapter;
    private MenuItem mMenuItem;

    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    Fragment3 fragment3 = new Fragment3();
    Fragment4 fragment4 = new Fragment4();
    List<Fragment> fragments;
    private Fragment fragment_now = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //去掉顶部标题
        getSupportActionBar().hide();
//        //去掉最上面状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
//                , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //顶部状态栏设置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //true=黑色字体  false=白色
        StatusBarUtil.setStatusBarDarkTheme(this, true);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);


        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mMenuItem = menuItem;
                switch (menuItem.getItemId()) {
                    case R.id.item_tab1:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.item_tab2:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.item_tab3:
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.item_tab4:
                        mViewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        List<Fragment> list = new ArrayList<>();
        list.add(fragment1.newInstance());
        list.add(fragment2.newInstance());
        list.add(fragment3.newInstance());
        list.add(fragment4.newInstance());

        mPagerAdapter.setList(list);

    }
}