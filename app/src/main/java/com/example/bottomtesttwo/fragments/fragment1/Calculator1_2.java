package com.example.bottomtesttwo.fragments.fragment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bottomtesttwo.R;

public class Calculator1_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator1_2);

        getSupportActionBar().hide();
    }

    public void calculator1_onClick2(View view) {
        switch (view.getId()) {
            case R.id.frag1_cal1_back_btn2:
                finish();
                break;
        }
    }
}
