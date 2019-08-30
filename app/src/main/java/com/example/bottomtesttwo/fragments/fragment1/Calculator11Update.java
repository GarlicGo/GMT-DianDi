package com.example.bottomtesttwo.fragments.fragment1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;

public class Calculator11Update extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator11_update);

        getSupportActionBar().hide();

        //非全屏活动设置
//        WindowManager.LayoutParams attributes = getWindow().getAttributes();
//        attributes.dimAmount = 0.50f; //设置窗口之外部分透明程度
//        attributes.x = 0;
//        attributes.y = 0;
//        attributes.width = 800;
//        attributes.height = 1024;
//        getWindow().setAttributes(attributes);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
    }
    public void calculator1_onClick2(View view) {
        switch (view.getId()) {
            case R.id.frag1_cal1_back_btn222://退格
                finish();
                break;
            case R.id.frag1_cal1_button1222://取消
                finish();
                break;
            case R.id.frag1_cal1_button2213://编辑、确定
                break;
        }
    }

}
