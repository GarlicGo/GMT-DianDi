package com.example.bottomtesttwo.fragments.login;

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
                loginButton();
                break;
            case R.id.login_sign:
                loginSign();
                break;
        }
    }

    private void loginButton(){
        if (loginPage == true){//登录页

            String username = editName.getText().toString();
            String password = editPassword.getText().toString();
            DBOperator dbOperator = DBOperator.getOperator();
            Cursor cursor = dbOperator.Query( "select * from user_info");
//            Cursor cursor = dbOperator.Query( "Select username From user_info Where username='"+username+"'");
            if(cursor.moveToFirst()){
                do{
                    Log.d("ZXY","login：成功进入do");
                    Log.d("ZXY","login："+cursor.getString(cursor.getColumnIndex("id")));

                    if(username.equals(cursor.getString(cursor.getColumnIndex("username")))){
                        Log.d("ZXY","login：成功进入if1");
                        Log.d("ZXY","login："+cursor.getString(cursor.getColumnIndex("password")));

                        if(password.equals(cursor.getString(cursor.getColumnIndex("password")))){
                            Log.d("ZXY","login：成功进入if2");
                            finish();
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                }while (cursor.moveToNext());
            }
            cursor.close();
            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
        }else if(loginPage == false){//注册页

        }
    }

    private void loginSign(){
        if (loginPage == true){//登录页→注册页
            editEmail.setVisibility(View.VISIBLE);
            editRpPassword.setVisibility(View.VISIBLE);
            textSign.setText("登录");
            loginButton.setText("注册");
            loginPage = false;
        }else if(loginPage == false){//注册页→登录页
            editEmail.setVisibility(View.GONE);
            editRpPassword.setVisibility(View.GONE);
            textSign.setText("注册");
            loginButton.setText("登录");
            loginPage = true;
        }
    }
}
