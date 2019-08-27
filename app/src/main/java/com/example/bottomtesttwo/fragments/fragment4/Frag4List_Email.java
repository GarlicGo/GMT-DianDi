package com.example.bottomtesttwo.fragments.fragment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bottomtesttwo.R;

public class Frag4List_Email extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button button;
    Button button2;
    EditText editText;
    LinearLayout layout;
    TextView textView1;
    TextView textView2;
    private boolean playOut = false;
    private boolean have = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag4_list__email);
        getSupportActionBar().hide();
        ImageView imageView = (ImageView)findViewById(R.id.frag4_list_email_back);

        imageView = (ImageView)findViewById(R.id.frag4_list_question_back);
        button = (Button)findViewById(R.id.frag4_list_phone_btn);
        editText = (EditText)findViewById(R.id.frag4_list_phone_edit1);
        layout = (LinearLayout)findViewById(R.id.frag4_list_phone_ll);
        textView1 = (TextView)findViewById(R.id.phone_bind_1);
        textView2 = (TextView)findViewById(R.id.phone_bind_2);
        button2 = (Button)findViewById(R.id.login_btn_login);

        button2.setOnClickListener(this);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag4_list_phone_btn:
                btnChange();
                break;
            case R.id.login_btn_login:
                addPhoneNumber();
                break;
            case R.id.frag4_list_email_back:
                finish();
                break;
        }
    }

    private void btnChange(){
        if(playOut == false){
            button.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            playOut = true;
        }else if(playOut == true){
            button.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            playOut = false;
        }
    }

    private void charge(){
        textView1.setText("你还未绑定手机号码");
        textView2.setVisibility(View.GONE);
        button.setText("绑定");
    }

    private void addPhoneNumber(){
        textView2.setVisibility(View.VISIBLE);
        textView2.setText(editText.getText().toString());
        button.setText("更换");
        btnChange();
    }
}
