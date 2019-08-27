package com.example.bottomtesttwo.fragments.fragment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareAdapter;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareItem;

import java.util.ArrayList;
import java.util.List;

public class Frag4List_Personal extends AppCompatActivity implements View.OnClickListener{

    Button button1;
    Button button2;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    EditText editText1;
    EditText editText2;
    EditText editText3;

    private boolean show = true;

    //RecyclerView
    List<SoftwareItem> softwareItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoftwareAdapter softwareAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag4_list__personal);
        getSupportActionBar().hide();



        ImageView imageView = (ImageView)findViewById(R.id.frag4_list_personal_back);
        button1 = (Button)findViewById(R.id.login_personal_button1);
        button2 = (Button)findViewById(R.id.login_personal_button2);
        linearLayout1 = (LinearLayout)findViewById(R.id.personal_ll_1);
        linearLayout2 = (LinearLayout)findViewById(R.id.personal_ll_2);
        editText1 = (EditText)findViewById(R.id.personal_edit_1);
        editText2 = (EditText)findViewById(R.id.personal_edit_2);
        editText3 = (EditText)findViewById(R.id.personal_edit_3);

        imageView.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        initData();
        recyclerView = (RecyclerView)findViewById(R.id.personal_recycle_view);
        layoutManager = new LinearLayoutManager(this);//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        softwareAdapter = new SoftwareAdapter(this,softwareItemList);
        recyclerView.setAdapter(softwareAdapter);
    }

    private void initData(){
        softwareItemList.add(new SoftwareItem("软件名称","点滴"));
        softwareItemList.add(new SoftwareItem("版本更新","（v 1.0.1）已是最新版本"));
        softwareItemList.add(new SoftwareItem("更新日期","2019年8月"));
        softwareItemList.add(new SoftwareItem("开发团队","枫华科技"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag4_list_personal_back:
                finish();
                break;
            case R.id.login_personal_button1:
                pageChange();
                break;
            case R.id.login_personal_button2:
                dataCalculate();
                pageChange();
                break;
        }
    }
    private void pageChange(){
        if(show == true){
            recyclerView.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.VISIBLE);
            show = false;
        }else if(show == false){
            recyclerView.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            show = true;
        }
    }

    private void dataCalculate(){
        String username = editText1.getText().toString();
        String birthDate = editText2.getText().toString();
        String personalSign = editText3.getText().toString();
    }
}
