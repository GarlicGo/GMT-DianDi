package com.example.bottomtesttwo.fragments.fragment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bottomtesttwo.R;

public class Frag4List_Question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag4_list__question);
        getSupportActionBar().hide();
        ImageView imageView = (ImageView)findViewById(R.id.frag4_list_question_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
