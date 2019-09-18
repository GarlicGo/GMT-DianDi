package com.example.bottomtesttwo.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.serverd.DBOperator;

import static com.example.bottomtesttwo.activity.MainActivity.setStatusBar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean loginPage = true;

    EditText editName;
    EditText editPassword;

    EditText editEmail;
    EditText editFirstPassword;
    EditText editRpPassword;

    Button loginButton;
    TextView textSign;

    public static LoginActivity instance;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        instance = this;

        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();
        //顶部状态栏设置属性（具体细节见MainActivity中的静态实现方法）
        //isPadding-是否预留出状态栏高度:true=是、false=fou;
        //dark:true=黑色字体  false=白色
        setStatusBar(this,false, false);


        editName = (EditText)findViewById(R.id.login_username);
        editFirstPassword = (EditText)findViewById(R.id.login_fpassword);

        editEmail = (EditText)findViewById(R.id.login_email);
        editPassword = (EditText)findViewById(R.id.login_password);
        editRpPassword = (EditText)findViewById(R.id.login_rpassword);

        loginButton = (Button)findViewById(R.id.login_btn_login);
        textSign = (TextView)findViewById(R.id.login_sign);

        loginButton.setOnClickListener(this);
        textSign.setOnClickListener(this);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        ownLogin();

    }


    //点击事件监听
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

    //检测是否本机是否处于登录状态(只检测ID)
    private void ownLogin(){

        if(pref.getString("id","").equals("") || pref.getString("id","").equals(null) || pref.getString("id","").equals("0")){
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //蓝色按钮点击
    private void loginButton(){
        if (loginPage == true){//登录页

            String email = editName.getText().toString();
            String password = editPassword.getText().toString();

            if(email.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 1");
                return;
            }else if(email.equals("")){//调用
                Toast.makeText(LoginActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 2");
                return;
            }

            if(email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){

            }else {
                Toast.makeText(this,"邮箱格式错误",Toast.LENGTH_SHORT).show();
                return;
            }


           if(password.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 4");
                return;
            }else if(password.equals("")){
                Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                Log.d("ZXY","else if 5");
                return;
            }else if(password.length() > 15){
               Toast.makeText(LoginActivity.this,"密码错误，最多15位",Toast.LENGTH_SHORT).show();
               return;
           }else if(password.length() < 6){
               Toast.makeText(LoginActivity.this,"密码错误，最少6位",Toast.LENGTH_SHORT).show();
               return;
           }

            Toast.makeText(this,"正在请求服务器...",Toast.LENGTH_SHORT).show();
            DBOperator dbOperator = DBOperator.getOperator();
            dbOperator.Seek(email,password);

        }else if(loginPage == false){//注册页
            Log.d("ZXY","注册页逻辑");

            String string1 = editEmail.getText().toString();
            String string2 = editFirstPassword.getText().toString();
            String string3 = editRpPassword.getText().toString();


            //判断邮箱格式
            if(string1.equals("") || string1.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                return;
            }

            if(string1.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
            {

            }
            else
            {
                Toast.makeText(LoginActivity.this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                return;
            }

            //判断密码格式
            if(string2.equals("") || string2.equals(null)){
                Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                return;
            }else if(string2.length() > 15){
                Toast.makeText(LoginActivity.this,"密码超长，最多15位",Toast.LENGTH_SHORT).show();
                return;
            }else if(string2.length() < 6){
                Toast.makeText(LoginActivity.this,"密码过短，最少6位",Toast.LENGTH_SHORT).show();
                return;
            }
            else if(string3.equals("") || string3.equals(null)){
                Toast.makeText(LoginActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                return;
            }

            //判断两个密码是否相等
            if(string2.equals(string3)){

                DBOperator dbOperator = DBOperator.getOperator();
                dbOperator.Add(string1,string2);

                prefEditor = pref.edit();
                prefEditor.putString("email",string1);
                prefEditor.putString("password",string2);
                prefEditor.apply();


                Toast.makeText(this,"正在请求服务器...",Toast.LENGTH_SHORT).show();
                return;
            }else {
                Toast.makeText(LoginActivity.this,"两次输入密码不同",Toast.LENGTH_SHORT).show();
                return;
            }
        }
//        ownLogin();
    }

    //UI转换方法
    private void loginSign(){
        if (loginPage == true){//登录页→注册页
            //显示注册页edit
            editEmail.setVisibility(View.VISIBLE);
            editRpPassword.setVisibility(View.VISIBLE);
            editFirstPassword.setVisibility(View.VISIBLE);
            //隐藏登录页edit
            editName.setVisibility(View.GONE);
            editPassword.setVisibility(View.GONE);

            textSign.setText("登录");
            loginButton.setText("注册");
            loginPage = false;
        }else if(loginPage == false){//注册页→登录页
            //隐藏注册页edit
            editEmail.setVisibility(View.GONE);
            editRpPassword.setVisibility(View.GONE);
            editFirstPassword.setVisibility(View.GONE);
            //显示登录页edit
            editName.setVisibility(View.VISIBLE);
            editPassword.setVisibility(View.VISIBLE);

            textSign.setText("注册");
            loginButton.setText("登录");
            loginPage = true;
        }
    }
}
