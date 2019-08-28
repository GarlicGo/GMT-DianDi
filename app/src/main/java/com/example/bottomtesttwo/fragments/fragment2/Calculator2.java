package com.example.bottomtesttwo.fragments.fragment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bottomtesttwo.R;

import static com.example.bottomtesttwo.activity.MainActivity.setStatusBar;

public class Calculator2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);


        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();

    }
}
