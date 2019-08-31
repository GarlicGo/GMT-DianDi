package com.example.bottomtesttwo.fragments.fragment1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.serverd.DBOperator;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class Calculator11Update extends AppCompatActivity {

    private long account_records_id;
    Intent intent;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    EditText editText8;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator11_update);
        editText1 = (EditText)findViewById(R.id.frag1_calculator1_1);
        editText2 = (EditText)findViewById(R.id.frag1_calculator1_2);
        editText3 = (EditText)findViewById(R.id.frag1_calculator1_3);
        editText4 = (EditText)findViewById(R.id.frag1_calculator1_4);
        editText5 = (EditText)findViewById(R.id.frag1_calculator1_5);
        editText6 = (EditText)findViewById(R.id.frag1_calculator1_6);
        editText7 = (EditText)findViewById(R.id.frag1_calculator1_7);
        editText8 = (EditText)findViewById(R.id.frag1_calculator1_8);

        getSupportActionBar().hide();
        //获取编辑子项ID
        intent = getIntent();

        initText();
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
                updateItem();
                Intent intentBack = new Intent();
                setResult(RESULT_OK,intentBack);
                finish();
                break;
            case R.id.frag1_cal1_back_btn333://删除
                AlertDialog.Builder builder = new Builder(this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("删除确认");
                builder.setMessage("确认删除此条信息吗？不可恢复。");
                builder.setPositiveButton("确定", new OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1){
                        delete();
                        finish();
                        Toast.makeText(Calculator11Update.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new OnClickListener(){
                    public void onClick(DialogInterface arg0,int arg1){

                    }
                });
                AlertDialog b = builder.create();
                b.show();//显示对话框
                break;
        }
    }

    private void initText(){
        //获取用户ID
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();

        account_records_id = intent.getLongExtra("account_records_id",0);
        Log.d("Calculator11UpdatE","account_records_id:"+account_records_id);

        //获取子项数据
        cursor = dbOperator.Query("select * from account_records where id='"+account_records_id+"'");

        if(cursor.moveToFirst()){

            //名称
            String title = cursor.getString(cursor.getColumnIndex("title"));
            //备注
            String description = cursor.getString(cursor.getColumnIndex("description"));
            //账号
            String accountNumber = cursor.getString(cursor.getColumnIndex("accountNumber"));
            //密码
            String password = cursor.getString(cursor.getColumnIndex("password"));
            //用户名
            String username = cursor.getString(cursor.getColumnIndex("username"));
            //绑定邮箱
            String email = cursor.getString(cursor.getColumnIndex("email"));
            //绑定手机
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            //身份证
            String IDNumber = cursor.getString(cursor.getColumnIndex("IDNumber"));

            if(title.equals("")){//名称
            }else {
                editText1.setText(title);
            }
            if(description.equals("")){
            }else {
                editText2.setText(description);
            }
            if(accountNumber.equals("")){
            }else {
                editText3.setText(accountNumber);
            }
            if(password.equals("")){
            }else {
                editText4.setText(password);
            }
            if(username.equals("")){
            }else {
                editText5.setText(username);
            }
            if(email.equals("")){
            }else {
                editText6.setText(email);
            }
            if(phoneNumber.equals("")){
            }else {
                editText7.setText(phoneNumber);
            }
            if(IDNumber.equals("")){
            }else {
                editText8.setText(IDNumber);
            }
        }

    }

    private void updateItem(){
        //获取用户ID
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        //获取子项ID
        account_records_id = intent.getLongExtra("account_records_id",0);
        Log.d("Calculator11UpdatE","account_records_id:"+account_records_id);
        //获取子项具体数据
        cursor = dbOperator.Query("select * from account_records where id='"+account_records_id+"'");
        cursor.moveToFirst();

            //名称
            String title = editText1.getText().toString();
            //备注
            String description = editText2.getText().toString();
            //账号
            String accountNumber = editText3.getText().toString();
            //密码
            String password = editText4.getText().toString();
            //用户名
            String username = editText5.getText().toString();
            //绑定邮箱
            String email = editText6.getText().toString();
            //绑定手机
            String phoneNumber = editText7.getText().toString();
            //身份证
            String IDNumber = editText8.getText().toString();

            if(title.equals("")){
                Toast.makeText(this,"名称不能为空",Toast.LENGTH_SHORT).show();
                return;
            }

            if(title.equals(cursor.getString(cursor.getColumnIndex("title")))){//名称
            }else {
                dbOperator.Cud("update account_records set title='"+title+"' where id='"+account_records_id+"'");
            }
            if(description.equals(cursor.getString(cursor.getColumnIndex("description")))){
            }else {
                dbOperator.Cud("update account_records set description='"+description+"' where id='"+account_records_id+"'");
            }
            if(accountNumber.equals(cursor.getString(cursor.getColumnIndex("accountNumber")))){
            }else {
                dbOperator.Cud("update account_records set accountNumber='"+accountNumber+"' where id='"+account_records_id+"'");
            }
            if(password.equals(cursor.getString(cursor.getColumnIndex("password")))){
            }else {
                dbOperator.Cud("update account_records set password='"+password+"' where id='"+account_records_id+"'");
            }
            if(username.equals(cursor.getString(cursor.getColumnIndex("username")))){
            }else {
                dbOperator.Cud("update account_records set username='"+username+"' where id='"+account_records_id+"'");
            }
            if(email.equals(cursor.getString(cursor.getColumnIndex("email")))){
            }else {
                dbOperator.Cud("update account_records set email='"+email+"' where id='"+account_records_id+"'");
            }
            if(phoneNumber.equals(cursor.getString(cursor.getColumnIndex("phoneNumber")))){
            }else {
                dbOperator.Cud("update account_records set phoneNumber='"+phoneNumber+"' where id='"+account_records_id+"'");
            }
            if(IDNumber.equals(cursor.getString(cursor.getColumnIndex("IDNumber")))){
            }else {
                dbOperator.Cud("update account_records set IDNumber='"+IDNumber+"' where id='"+account_records_id+"'");
            }

    }

    private void delete(){
        //获取子项ID
        account_records_id = intent.getLongExtra("account_records_id",0);
        Log.d("Calculator11UpdatE","account_records_id:"+account_records_id);
        dbOperator.Cud("delete from account_records where id='"+account_records_id+"'");
        Intent intentBack = new Intent();
        setResult(RESULT_OK,intentBack);
    }

}
