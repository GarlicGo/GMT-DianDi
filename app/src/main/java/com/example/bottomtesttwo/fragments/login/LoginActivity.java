package com.example.bottomtesttwo.fragments.login;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.serverd.DBOperator;
import com.example.bottomtesttwo.serverd.DBSyncer;

import static com.example.bottomtesttwo.activity.MainActivity.setStatusBar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean loginPage = true;
    private boolean loginOline = false;
    EditText editEmail;
    EditText editName;
    EditText editPassword;
    EditText editRpPassword;
    Button loginButton;
    TextView textSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();
        //顶部状态栏设置属性（具体细节见MainActivity中的静态实现方法）
        //isPadding-是否预留出状态栏高度:true=是、false=fou;
        //dark:true=黑色字体  false=白色
        setStatusBar(this,false, false);

        editEmail = (EditText)findViewById(R.id.login_email);
        editName = (EditText)findViewById(R.id.login_username);
        editPassword = (EditText)findViewById(R.id.login_password);
        editRpPassword = (EditText)findViewById(R.id.login_rpassword);
        loginButton = (Button)findViewById(R.id.login_btn_login);
        textSign = (TextView)findViewById(R.id.login_sign);

        loginButton.setOnClickListener(this);
        textSign.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login:
                Log.d("ZXY","onclick：R.id.login_btn_login");
                loginButton();
                break;
            case R.id.login_sign:
                Log.d("ZXY","onclick：R.id.login_sign");
                loginSign();
                break;
        }
    }

    private void loginButton(){
        if (loginPage == true){//登录页

            boolean idSuccess = false;
            boolean passwordSuccess = false;
            String idString = editName.getText().toString();
            String password = editPassword.getText().toString();

            if(idString.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 1");
                return;
            }else if(idString.equals("")){//调用
                Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 2");
                return;
            }else if(idString.length()!=8){
                Toast.makeText(LoginActivity.this,"请正确输入账号",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 3");
                return;
            }else if(password.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 4");
                return;
            }else if(password.equals("")){
                Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 5");
                return;
            }

            int id = Integer.parseInt(idString);
            DBSyncer dbSyncer = DBSyncer.getSyncer();
            dbSyncer.start(id);
            Log.d("ZXY","id："+id);

            DBOperator dbOperator = DBOperator.getOperator();
            Cursor cursor = dbOperator.Query( "select * from user_info");
//            Log.d("ZXY","id INT："+cursor.getColumnIndex("id"));
            if(cursor.moveToFirst()){
                do{
                    Log.d("ZXY","login：成功进入do");
                    Log.d("ZXY","login："+cursor.getString(cursor.getColumnIndex("id")));
                    Log.d("ZXY","login："+cursor.getString(cursor.getColumnIndex("password")));

                    if(id == cursor.getInt(cursor.getColumnIndex("id"))){
                        Log.d("ZXY","login：成功进入if1");
                        idSuccess = true;
                        if(password.equals(cursor.getString(cursor.getColumnIndex("password")))){
                            Log.d("ZXY","login：成功进入if2");
                            finish();
                            passwordSuccess = true;
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                }while (cursor.moveToNext());
            }
            cursor.close();

            if(idSuccess == false){
                Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
            }else if(idSuccess==true && passwordSuccess==false){
                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }

        }else if(loginPage == false){//注册页
            Log.d("ZXY","注册页逻辑");

            String string1 = editEmail.getText().toString();
            String string2 = editPassword.getText().toString();
            String string3 = editRpPassword.getText().toString();


            if(string1.equals("") || string1.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                return;
            }else if(string2.equals("") || string2.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                return;
            }else if(string3.equals("") || string3.equals(null)){
                Toast.makeText(LoginActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                return;
            }

            if(string2.equals(string3)){

                DBOperator dbOperator = DBOperator.getOperator();
                dbOperator.Cud( "insert into user_info (password,email) values ('"+string2+"','"+string1+"')");
                finish();
                Toast.makeText(LoginActivity.this,"注册成功，自动登录",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(LoginActivity.this,"两次输入密码不同",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void loginSign(){
        if (loginPage == true){//登录页→注册页
            editEmail.setVisibility(View.VISIBLE);
            editRpPassword.setVisibility(View.VISIBLE);
            editName.setVisibility(View.GONE);
            textSign.setText("登录");
            loginButton.setText("注册");
            loginPage = false;
        }else if(loginPage == false){//注册页→登录页
            editEmail.setVisibility(View.GONE);
            editRpPassword.setVisibility(View.GONE);
            editName.setVisibility(View.VISIBLE);
            textSign.setText("注册");
            loginButton.setText("登录");
            loginPage = true;
        }
    }
}
