package com.example.bottomtesttwo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.Fragment1;
import com.example.bottomtesttwo.fragments.Fragment2;
import com.example.bottomtesttwo.fragments.Fragment3;
import com.example.bottomtesttwo.fragments.Fragment4;
import com.example.bottomtesttwo.fragments.fragment1.Calculator1_1;
import com.example.bottomtesttwo.fragments.fragment1.Calculator1_2;
import com.example.bottomtesttwo.fragments.fragment2.Calculator2;
import com.example.bottomtesttwo.fragments.fragment3.Calculator;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item1;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item2;
import com.example.bottomtesttwo.fragments.login.LoginActivity;
import com.example.bottomtesttwo.serverd.DBOperator;
import com.example.bottomtesttwo.serverd.DBSyncer;
import com.example.bottomtesttwo.util.StatusBar.StatusBarUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private BottomNavigationView mBottomNavigationView;//用于接收底部菜单栏实体
    private MenuItem mMenuItem;//用于获取菜单栏当前处于哪一个位置
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    public static MainActivity instance = null;

    private boolean loginOlineState = false;
//    private Fragment1 fragment1 = new Fragment1();
//    private Fragment2 fragment2 = new Fragment2();
//    private Fragment3 fragment3 = new Fragment3();
//    private Fragment4 fragment4 = new Fragment4();
//    List<Fragment> fragments = new

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //LitePal.getDatabase();
        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();
        //顶部状态栏设置属性（具体细节见MainActivity中的静态实现方法）
        //isPadding-是否预留出状态栏高度:true=是、false=fou;
        //dark:true=黑色字体  false=白色
        setStatusBar(this,false, true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Fragment1());//默认加载fragment1页面

//        login();
        instance = this;

//        DBOperator dbOperator = DBOperator.getOperator();
//        Cursor cursor;
//        int id ;
//        cursor = dbOperator.Query( "select * from user_info");
//        cursor.moveToFirst();
//        id = cursor.getInt(cursor.getColumnIndex("id"));
//        cursor.close();
//        DBSyncer dbSyncer = DBSyncer.getSyncer();
//        dbSyncer.start(id);

        //底部菜单栏实体
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        //悬浮添加按钮
        final FloatingActionButton addBottom = (FloatingActionButton)findViewById(R.id.float_add);

        addBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMenuItem == null){
                    Fragment1 fragment1Temp = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                    if(fragment1Temp.isFrag1FirstChange()){
                        Intent intent11 = new Intent(MainActivity.this, Calculator1_1.class);
                        startActivityForResult(intent11,1);
                    }else {
                        Intent intent12 = new Intent(MainActivity.this, Calculator1_2.class);
                        startActivityForResult(intent12,1);
                    }
                }else {
                    switch (mMenuItem.getItemId()){
                        case R.id.item_tab1:
                            Fragment1 fragment1Temp = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                            if(fragment1Temp.isFrag1FirstChange()){
                                Intent intent11 = new Intent(MainActivity.this, Calculator1_1.class);
                                startActivityForResult(intent11,1);
                            }else {
                                Intent intent12 = new Intent(MainActivity.this, Calculator1_2.class);
                                startActivityForResult(intent12,1);
                            }
                            break;
                        case R.id.item_tab2:
                            Intent intent2 = new Intent(MainActivity.this, Calculator2.class);
                            startActivityForResult(intent2,2);
                            break;
                        case R.id.item_tab3:
                            Intent intent3 =new Intent(MainActivity.this, Calculator.class);
                            startActivityForResult(intent3,3);
                            break;
                    }
                }
            }
        });

        //底部菜单栏点击监听事件
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mMenuItem = menuItem;
                switch (menuItem.getItemId()) {

                    case R.id.item_tab1://底部导航栏卡包点击事件相应
                        replaceFragment(fragment1);
                        setStatusBar(MainActivity.this,false, true);
                        addBottom.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.item_tab2://底部导航栏存钱点击事件相应
                        replaceFragment(fragment2);
                        setStatusBar(MainActivity.this,false, false);
                        addBottom.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.item_tab3://底部导航栏记账点击事件相应
                        replaceFragment(fragment3);
                        setStatusBar(MainActivity.this,false, false);
                        addBottom.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.item_tab4://底部导航栏我点击事件相应
                        replaceFragment(fragment4);
                        setStatusBar(MainActivity.this,false, true);
                        addBottom.setVisibility(View.GONE);
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


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                Log.d("ZXYBACK","case 1");
                if(resultCode == RESULT_OK){
                    Log.d("ZXYBACK","case 1 OK");
                    Fragment1 fragment1Temp = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                    fragment1Temp.initItem1List();
                    Toast.makeText(this,"you did it",Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                break;
            case 3:
                if(resultCode == RESULT_OK){
                    int firstType = data.getIntExtra("firstType",0);//选择支出/收入，1为支出，2为收入
                    int secondType = data.getIntExtra("secondType",0);//选择小类别
                    //String moneyCard = (int) data.getShortExtra("moneyCard");//支付卡包类别
                    double moneyNumber = data.getDoubleExtra("moneyNumber",0.00);//记录用户存入钱数
                    String tip = data.getStringExtra("tip");
                    String accountingDate = data.getStringExtra("accountingDate");//记录日期（2019年8月22日）
                    addItem(tip,secondType,moneyNumber,accountingDate.substring(5,accountingDate.length()));
                }
                break;
        }
    }


    private boolean isAddNew = true;
//    Fragment3 fragment3Control = (Fragment3)getSupportFragmentManager().findFragmentById(R.id.float_add);
    public void addItem(String tip,int imageId,double number,String date){
        fragment3.addFrag3Item(tip,imageId,number,date);
    }

    private void login(){
        if(loginOlineState == false){
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        textDate.setText(String.format("%d年%d月",year,month+1));
        fragment3.textDate.setText(String.format("%d年%d月",year,month+1));

    }
}