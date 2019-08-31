package com.example.bottomtesttwo.fragments.fragment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.time.LocalDate;
import java.util.Calendar;

public class Calculator1_1 extends AppCompatActivity {

    EditText title;//名称
    EditText description;//备注
    EditText accountNumber;//账号
    EditText password;//密码
    EditText username;//用户名
    EditText email;//绑定邮箱
    EditText phoneNumber;//绑定手机
    EditText IDNumber;//绑定身份证

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator1_1);

        getSupportActionBar().hide();

        title = (EditText)findViewById(R.id.frag1_calculator1_1);
        description = (EditText)findViewById(R.id.frag_calculator1_2);
        accountNumber = (EditText)findViewById(R.id.frag_calculator1_3);
        password = (EditText)findViewById(R.id.frag_calculator1_4);
        username = (EditText)findViewById(R.id.frag_calculator1_5);
        email = (EditText)findViewById(R.id.frag_calculator1_6);
        phoneNumber = (EditText)findViewById(R.id.frag_calculator1_7);
        IDNumber = (EditText)findViewById(R.id.frag_calculator1_8);

//        cursor = dbOperator.Query( "select * from account_records where id='2019831'");
//        cursor.moveToFirst();
//        Log.d("ZXYDATE","sqlite id : "+cursor.getString(cursor.getColumnIndex("title")));
//        cursor.close();
    }


    public void calculator1_onClick1(View view) {
        switch (view.getId()) {
            case R.id.frag1_cal1_back_btn:
                doBack();
                break;
            case R.id.frag1_cal1_button1:
                doBack();
                break;
            case R.id.frag1_cal1_button2:
                if(title.getText().toString().equals("")){
                    Toast.makeText(this,"名称不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    addAccountItem();
                    Intent intent = new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
                break;
        }
    }

    private void addAccountItem(){
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

        Log.d("ZXYDATE",":"+date);

        dbOperator.Cud("insert into account_records (id,title,description,accountNumber,password,username,email,phoneNumber,IDNumber,user_info_id) values ('"
                +date+"','"
                +title.getText().toString()+ "','"
                +description.getText().toString()+"','"
                +accountNumber.getText().toString()+"','"
                +password.getText().toString()+"','"
                +username.getText().toString()+"','"
                +email.getText().toString()+"','"
                +phoneNumber.getText().toString()+"','"
                +IDNumber.getText().toString()+"','"
                +id+"')");

        cursor = dbOperator.Query( "select * from account_records where id='"+date+"'");
        cursor.moveToFirst();
        Log.d("ZXYDATE","sqlite id : "+cursor.getString(cursor.getColumnIndex("title")));
        cursor.close();


    }

    public void doBack(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}
