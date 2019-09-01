package com.example.bottomtesttwo.fragments.fragment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareAdapter;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareItem;

import java.util.ArrayList;
import java.util.List;

public class Frag4List_Software extends AppCompatActivity {

    //RecyclerView
    List<SoftwareItem> softwareItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoftwareAdapter softwareAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag4_list__software);
        getSupportActionBar().hide();

        ImageView imageView = (ImageView)findViewById(R.id.frag4_list_software_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
        recyclerView = (RecyclerView)findViewById(R.id.frag4_list_software_recycle);
        layoutManager = new LinearLayoutManager(this);//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        softwareAdapter = new SoftwareAdapter(this,softwareItemList);
        recyclerView.setAdapter(softwareAdapter);

    }

    private void initData(){
        softwareItemList.add(new SoftwareItem("软件名称","点滴"));
        softwareItemList.add(new SoftwareItem("版本更新","（功能版）v 1.0.1"));
        softwareItemList.add(new SoftwareItem("更新日期","2019年9月"));
        softwareItemList.add(new SoftwareItem("开发团队","枫华科技"));
//        softwareItemList.add(new SoftwareItem("客户水群","262556715"));
    }

}
