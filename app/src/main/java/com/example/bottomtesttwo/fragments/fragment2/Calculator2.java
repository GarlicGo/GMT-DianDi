package com.example.bottomtesttwo.fragments.fragment2;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment3.Calculator;

import java.util.Calendar;

import static com.example.bottomtesttwo.activity.MainActivity.setStatusBar;

public class Calculator2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);


        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();

    }

    public void calculator2_onClick(View view) {
//        Toast.makeText(this,"you clicked ",Toast.LENGTH_SHORT).;
        switch (view.getId()) {
            case R.id.calculator2_num_0:
                break;
            case R.id.calculator2_num_1:
                break;
            case R.id.calculator2_num_2:
                break;
            case R.id.calculator2_num_3:
                break;
            case R.id.calculator2_num_4:
                break;
            case R.id.calculator2_num_5:
                break;
            case R.id.calculator2_num_6:
                break;
            case R.id.calculator2_num_7:
                break;
            case R.id.calculator2_num_8:
                break;
            case R.id.calculator2_num_9:
                break;
            case R.id.calculator2_num_dot:
                break;
            case R.id.calculator2_num_del://退格
                break;
            case R.id.calculator2_num_done://确定
                break;
            case R.id.calculator2_clear://清空
                break;
        }
    }
}
