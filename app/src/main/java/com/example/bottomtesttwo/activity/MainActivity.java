package com.example.bottomtesttwo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.Fragment1;
import com.example.bottomtesttwo.fragments.Fragment2;
import com.example.bottomtesttwo.fragments.Fragment3;
import com.example.bottomtesttwo.fragments.Fragment4;
import com.example.bottomtesttwo.fragments.fragment1.Calculator11Update;
import com.example.bottomtesttwo.fragments.fragment1.Calculator1_1;
import com.example.bottomtesttwo.fragments.fragment1.Calculator1_2;
import com.example.bottomtesttwo.fragments.fragment2.AddTarget;
import com.example.bottomtesttwo.fragments.fragment2.Calculator2;
import com.example.bottomtesttwo.fragments.fragment2.TargetListActivity;
import com.example.bottomtesttwo.fragments.fragment3.Calculator;
import com.example.bottomtesttwo.activity.login.LoginActivity;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Personal;
import com.example.bottomtesttwo.fragments.fragment4.HeadImage;
import com.example.bottomtesttwo.serverd.DBOperator;
import com.example.bottomtesttwo.util.StatusBar.StatusBarUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,View.OnClickListener {

    private BottomNavigationView mBottomNavigationView;//用于接收底部菜单栏实体
    private MenuItem mMenuItem;//用于获取菜单栏当前处于哪一个位置
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    public FloatingActionButton addBottom;

    public static final int TAKE_PHOTO = 100;
    public static final int CHOOSE_PHOTO = 200;
    private ImageView picture;
    private Uri imageUri;

    public static MainActivity instance = null;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

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

        instance = this;
//        LoginActivity.instance.finish();


        //底部菜单栏实体
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        //悬浮添加按钮
        addBottom = (FloatingActionButton)findViewById(R.id.float_add);;

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
                        startActivityForResult(intent12,21);
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
                        setStatusBar(MainActivity.this,false, false);
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
                if(resultCode == RESULT_OK){
                    Log.d("ZXYBACK","case 1 OK");
                    Fragment2 fragment2Temp = (Fragment2)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                    fragment2Temp.init();
//                    Toast.makeText(this,"you did it",Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if(resultCode == RESULT_OK){
                    Fragment3 fragment3Temp = (Fragment3)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                    fragment3Temp.initFrag3Item();
                }
                break;
            case 20:
                if(resultCode == RESULT_OK){
                    Fragment1 fragment1Temp = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                    fragment1Temp.initItem1List();
                }
                break;
            case 21:
                if(resultCode == RESULT_OK){
                    Fragment1 fragment1Temp = (Fragment1)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                    fragment1Temp.initItem2List();
                }
                break;
            case 33:

//                Toast.makeText(this,"33",Toast.LENGTH_SHORT).show();
                break;

            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:

                break;

            case 666:
                Fragment4 fragment4Temp = (Fragment4)getSupportFragmentManager().findFragmentById(R.id.bolck_fragmelayout);
                fragment4Temp.loadImage();
                break;
            default:
                break;
        }
    }


    private boolean isAddNew = true;
//    Fragment3 fragment3Control = (Fragment3)getSupportFragmentManager().findFragmentById(R.id.float_add);
    public void addItem(String tip,int imageId,double number,long date){
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
        fragment3.frag3Date = String.format("%02d%02d00",year%100,(month+1)%100);
//        Toast.makeText(this,"11",Toast.LENGTH_SHORT).show();
        fragment3.initFrag3Item();
    }

    public void calculator2_onClick1(View view){
        switch (view.getId()){
            case R.id.frag2_2_1:
                Intent intent221 = new Intent(this, TargetListActivity.class);
                startActivityForResult(intent221,2);
                break;
            case R.id.frag2_2_2:
                Intent intent222 = new Intent(this, AddTarget.class);
                startActivityForResult(intent222,33);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag4_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("登录退出");
                builder.setMessage("确定要退出本账号吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1){
                        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        prefEditor = pref.edit();
                        prefEditor.putString("id","0");
                        prefEditor.putString("email","0");
                        prefEditor.putString("password","0");
                        prefEditor.apply();

                        Intent outIntent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(outIntent);
                        finish();
                        Toast.makeText(MainActivity.this,"推出成功",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface arg0,int arg1){

                    }
                });
                AlertDialog b = builder.create();
                b.show();//显示对话框
                break;
            case R.id.frag4_user_info:
//                Intent userinfoIntent = new Intent(this, Frag4List_Personal.class);
//                startActivity(userinfoIntent);
                break;
            case R.id.second_head:
//                if(ContextCompat.checkSelfPermission(MainActivity.instance, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(MainActivity.instance,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//                }else {
//                    openAlbum();
//                }
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
//                } else {
//                    openAlbum();
//                }
                Intent intentHead = new Intent(this, HeadImage.class);
                startActivityForResult(intentHead,666);
                break;
        }
    }



}