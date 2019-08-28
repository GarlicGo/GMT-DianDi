package com.example.bottomtesttwo.fragments.fragment4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareAdapter;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareItem;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.ArrayList;
import java.util.List;

public class Frag4List_Personal extends AppCompatActivity implements View.OnClickListener{

    Button button1;
    Button button2;
    Button button3;
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
        button3 = (Button)findViewById(R.id.login_personal_button3);
        linearLayout1 = (LinearLayout)findViewById(R.id.personal_ll_1);
        linearLayout2 = (LinearLayout)findViewById(R.id.personal_ll_2);
        editText1 = (EditText)findViewById(R.id.personal_edit_1);
        editText2 = (EditText)findViewById(R.id.personal_edit_2);
        editText3 = (EditText)findViewById(R.id.personal_edit_3);

        imageView.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);



        initData();
        recyclerView = (RecyclerView)findViewById(R.id.personal_recycle_view);
        layoutManager = new LinearLayoutManager(this);//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        softwareAdapter = new SoftwareAdapter(this,softwareItemList);
        recyclerView.setAdapter(softwareAdapter);
    }

    private void initData(){
        DBOperator dbOperator = DBOperator.getOperator();
        Cursor cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        if(cursor.getString(cursor.getColumnIndex("id")).equals("")){

        }else {
            addItem("ID",cursor.getString(cursor.getColumnIndex("id")));
        }
        if(cursor.getString(cursor.getColumnIndex("username")).equals("")){

        }else {
            addItem("用户名",cursor.getString(cursor.getColumnIndex("username")));
        }
        if(cursor.getString(cursor.getColumnIndex("gender")).equals("")){

        }else {
            String genderTemp = cursor.getString(cursor.getColumnIndex("gender"));
            if (genderTemp.equals("0")){
                addItem("性别","女");
            }else if(genderTemp.equals("1")){
                addItem("性别","男");
            }
        }
        if(cursor.getString(cursor.getColumnIndex("personalSignature")).equals("")){

        }else {
            addItem("个性签名",cursor.getString(cursor.getColumnIndex("personalSignature")));
        }
        if(cursor.getString(cursor.getColumnIndex("birthday")).equals("")){

        }else {
            addItem("出生日期",cursor.getString(cursor.getColumnIndex("birthday")));
        }
        if(cursor.getString(cursor.getColumnIndex("phoneNumber")).equals("")){
            Log.d("ZXY","空");
            Log.d("ZXY","空"+cursor.getString(cursor.getColumnIndex("phoneNumber")));
        }else {
            Log.d("ZXY","非空");
            addItem("绑定手机",cursor.getString(cursor.getColumnIndex("phoneNumber")));
        }
        if(cursor.getString(cursor.getColumnIndex("email")).equals("")){

        }else {
            addItem("绑定邮箱",cursor.getString(cursor.getColumnIndex("email")));
        }
        cursor.close();


    }

    private void addItem(String string1,String string2){
        softwareItemList.add(new SoftwareItem(string1,string2));
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
            case R.id.login_personal_button3:
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
