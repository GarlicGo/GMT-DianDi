package com.example.bottomtesttwo.fragments.fragment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bottomtesttwo.R;

public class Frag4List_Secret extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag4_list_secret);
        getSupportActionBar().hide();

        imageView = (ImageView)findViewById(R.id.frag4_list_secret_back);
        imageView.setOnClickListener(this);
        editText1 = (EditText)findViewById(R.id.secret_text_1);
        editText2 = (EditText)findViewById(R.id.secret_text_2);
        editText3 = (EditText)findViewById(R.id.secret_text_3);
        button = (Button)findViewById(R.id.login_secret_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag4_list_secret_back:
                finish();
                break;
            case R.id.login_secret_button:
                editData();
                break;
        }
    }

    private void editData(){
        String oldSecret = editText1.getText().toString();
        String newSecret = editText2.getText().toString();
        String rpSecret = editText3.getText().toString();
    }


}
