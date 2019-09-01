package com.example.bottomtesttwo.fragments.fragment2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.fragment1.Calculator11Update;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1Adapter;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Adapter;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.ArrayList;
import java.util.List;

public class TargetListActivity extends AppCompatActivity {

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor = dbOperator.Query( "select * from user_info");
    private int id;

    TargetListActivity instance = null;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<TargetItem> targetItemList = new ArrayList<>();
    TargetAdapter targetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_list);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView)findViewById(R.id.frag2_target_recycle);
        layoutManager = new LinearLayoutManager(TargetListActivity.this);//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        targetAdapter = new TargetAdapter(TargetListActivity.this,targetItemList);
        recyclerView.setAdapter(targetAdapter);

        init();

        instance = this;

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                targetAdapter.setOnclick(new TargetAdapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {

                        TargetItem targetItem = targetItemList.get(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(TargetListActivity.this);
                        builder.setIcon(null);//设置图标, 这里设为空值
                        builder.setTitle("选中确认");
                        builder.setMessage("确认选择"+targetItem.getName()+"为当前目标吗？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface arg0, int arg1){
                                cursor = dbOperator.Query( "select * from user_info");
                                cursor.moveToFirst();
                                id = cursor.getInt(cursor.getColumnIndex("id"));
                                cursor.close();
                                dbOperator.Cud("update plan_info set originCard='0' where user_info_id='"+id+"'");
                                dbOperator.Cud("update plan_info set originCard='1' where id='"+targetItem.getId()+"'");
                                Intent intent = new Intent();
                                setResult(RESULT_OK,intent);
                                finish();
//                                Toast.makeText(Calculator11Update.this,"删除成功！",Toast.LENGTH_SHORT).show();

                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface arg0,int arg1){

                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    }
                });
            }
        });


    }

    public void frag2_target_onClick(View view) {
        switch (view.getId()) {
            case R.id.frag2_cal1_back_btn234343:
                finish();
                break;
        }
    }

    private void init(){
        //获取用户ID
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        cursor = dbOperator.Query("select * from plan_info where user_info_id='"+id+"'");
        if(cursor.moveToFirst()){
            Log.d("TARGET","成功进入move to first");
            do {
                int plan_info_id = cursor.getInt(cursor.getColumnIndex("id"));
                double expectantAmount = cursor.getDouble(cursor.getColumnIndex("expectantAmount"));
                int starTime = cursor.getInt(cursor.getColumnIndex("startTime"));
                int endTime = cursor.getInt(cursor.getColumnIndex("endTime"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                TargetItem targetItem = new TargetItem(title,expectantAmount,starTime,endTime);
                targetItem.setId(plan_info_id);
                targetItemList.add(targetItem);

            }while (cursor.moveToNext());
        }else {
            Log.d("TARGET","未成功进入move to first");
        }
        cursor.close();
        recyclerView.setAdapter(targetAdapter);
    }

}
