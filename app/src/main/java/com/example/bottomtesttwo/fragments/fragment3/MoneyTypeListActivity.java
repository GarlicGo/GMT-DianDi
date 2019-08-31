package com.example.bottomtesttwo.fragments.fragment3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.fragment1.Calculator11Update;
import com.example.bottomtesttwo.fragments.fragment1.Calculator12Update;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1Adapter;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item2;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item2Adapter;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.ArrayList;
import java.util.List;

public class MoneyTypeListActivity extends AppCompatActivity {

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    //RecyclerView
    List<Frag1Item2> frag1Item1List_2 = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag1Item2Adapter frag1Item1Adapter_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_type_list);
        getSupportActionBar().hide();

        initItem2List();
        recyclerView = (RecyclerView)findViewById(R.id.frag3_moneytype_recycle);
        layoutManager = new LinearLayoutManager(MoneyTypeListActivity.this);//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag1Item1Adapter_2 = new Frag1Item2Adapter(MoneyTypeListActivity.this,frag1Item1List_2);
        recyclerView.setAdapter(frag1Item1Adapter_2);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                frag1Item1Adapter_2.setOnclick(new Frag1Item2Adapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Frag1Item2 frag1Item2 = frag1Item1List_2.get(position);

                        Intent intent = new Intent();
                        intent.putExtra("name",frag1Item2.getName());
                        intent.putExtra("card_id",frag1Item2.getCardRecordsId());
                        setResult(RESULT_OK,intent);
                        finish();
//                        AlertDialog.Builder builder = new AlertDialog.Builder(MoneyTypeListActivity.this);
//                        builder.setIcon(null);//设置图标, 这里设为空值
//                        builder.setTitle("确认选择");
//                        builder.setMessage("确认选择"+frag1Item2.getName()+"为当前目标吗？");
//                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
//                            public void onClick(DialogInterface arg0, int arg1){
//                                Toast.makeText(MoneyTypeListActivity.this,"id:"+frag1Item2.getCardRecordsId(),
//                                        Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        });
//                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
//                            public void onClick(DialogInterface arg0,int arg1){
//
//                            }
//                        });
//                        AlertDialog b = builder.create();
//                        b.show();//显示对话框
                    }
                });
            }
        });

    }



    public void frag3_moneyType_onclick(View view) {
        switch (view.getId()) {
            case R.id.frag1_cal1_back_btn222423://退格
                finish();
                break;
        }
    }

    public void initItem2List(){
        frag1Item1List_2.clear();
        Log.d("ZXYFrag1","成功调用initItemList");
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor = dbOperator.Query( "select * from card_records where user_info_id='"+id+"'");
        if(cursor.moveToFirst()){
            do{
                long card_records_id = cursor.getLong(cursor.getColumnIndex("id"));
                int icon = cursor.getInt(cursor.getColumnIndex("icon"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                double balance = cursor.getDouble(cursor.getColumnIndex("balance"));

                switch (icon){
                    case 1:
                        icon = R.mipmap.frag1_cal1_xianjin;
                        break;
                    case 2:
                        icon = R.mipmap.frag1_cal1_chuxvka;
                        break;
                    case 3:
                        icon = R.mipmap.frag1_cal1_xinyongka;
                        break;
                    case 4:
                        icon = R.mipmap.frag1_cal1_zhifubao;
                        break;
                    case 5:
                        icon = R.mipmap.frag1_cal1_weixin;
                        break;
                    case 6:
                        icon = R.mipmap.frag1_cal1_xiaoyuanka;
                        break;
                    default:
                        icon = R.mipmap.frag1_cal2_default;
                        break;
                }
                frag1Item1List_2.add(new Frag1Item2(card_records_id,icon,title,balance));
            }while (cursor.moveToNext());
        }
    }

}
