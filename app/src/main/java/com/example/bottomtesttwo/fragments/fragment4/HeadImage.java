package com.example.bottomtesttwo.fragments.fragment4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment2.TargetAdapter;
import com.example.bottomtesttwo.fragments.fragment2.TargetItem;
import com.example.bottomtesttwo.fragments.fragment2.TargetListActivity;
import com.example.bottomtesttwo.fragments.fragment4.List.HeadAdapter;
import com.example.bottomtesttwo.fragments.fragment4.List.HeadItem;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.ArrayList;
import java.util.List;

public class HeadImage extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private List<HeadItem> headItemList = new ArrayList<>();

    private HeadAdapter adapter;
    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image);
        //去掉顶部标题(状态栏下面带有顶部返回按钮的那个)
        getSupportActionBar().hide();

        initImage();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.head_recycleView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HeadAdapter(headItemList);
        recyclerView.setAdapter(adapter);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setOnclick(new HeadAdapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //获取用户ID
                        cursor = dbOperator.Query( "select * from user_info");
                        cursor.moveToFirst();
                        id = cursor.getInt(cursor.getColumnIndex("id"));
                        cursor.close();

                        dbOperator.Cud("update user_info set avatar='"+position+"' where id='"+id+"'");
                        finish();

                    }
                });
            }
        });
    }

    private void initImage(){
        headItemList.clear();
        headItemList.add(new HeadItem("头像1",R.mipmap.head1));
        headItemList.add(new HeadItem("头像2",R.mipmap.head2));
    }






}
