package com.example.bottomtesttwo.fragments.fragment1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.Calendar;

public class Calculator1_2 extends AppCompatActivity {

    EditText editText1;
    EditText editText2;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator1_2);

        getSupportActionBar().hide();

        editText1 = (EditText)findViewById(R.id.frag1_calculator2_1);
        editText2 = (EditText)findViewById(R.id.frag1_calculator2_2);
    }

    public void calculator1_onClick2(View view) {
        switch (view.getId()) {
            case R.id.frag1_cal1_back_btn2://后退
                finish();
                break;
            case R.id.frag1_cal2_button1://取消
                finish();
                break;
            case R.id.frag1_cal2_button2://确认
                addItem();
                break;
        }
    }

    private void addItem(){
        String string1 = editText1.getText().toString();
        String string2 = editText2.getText().toString();
        if(string1.equals("")){
            Toast.makeText(this,"名称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else if(string2.equals("")){
            //余额输入为空默认为0
            string2 = "0";
        }else {
            if(string2.matches("(^-?[1-9](\\d+)?(\\.\\d{1,2})?$)|(^-?0$)|(^-?\\d\\.\\d{1,2}$)")){
//                Toast.makeText(this,"正确",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"余额格式错误(精确之小数点后两位)",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //获取用户ID
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();

        //获取系统的日期
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);
        String date = ""+(year%100)+month+day+hour+minute+second;

        dbOperator.Cud("insert into card_records (id,icon,title,balance,user_info_id) values ('"
                +date+"','0','"
                +string1+"','"
                +string2+"','"
                +id+"')");

        Intent intentBack = new Intent();
        setResult(RESULT_OK,intentBack);
        finish();
    }
}
