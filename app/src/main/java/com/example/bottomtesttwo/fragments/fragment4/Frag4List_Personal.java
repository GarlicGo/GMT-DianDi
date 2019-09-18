package com.example.bottomtesttwo.fragments.fragment4;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareAdapter;
import com.example.bottomtesttwo.fragments.fragment4.List.SoftwareItem;
import com.example.bottomtesttwo.serverd.DBOperator;
import com.example.bottomtesttwo.util.SoftInputUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Frag4List_Personal extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button button1;
    Button button2;
    Button button3;
    Button buttonBirthday;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    CheckBox checkBox0;
    CheckBox checkBox1;


    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id;

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

        Calendar calendar = Calendar.getInstance();


        ImageView imageView = (ImageView)findViewById(R.id.frag4_list_personal_back);
        button1 = (Button)findViewById(R.id.login_personal_button1);
        button2 = (Button)findViewById(R.id.login_personal_button2);
        button3 = (Button)findViewById(R.id.login_personal_button3);
//        buttonBirthday = (Button)findViewById(R.id.personal_btn_birth);
        linearLayout1 = (LinearLayout)findViewById(R.id.personal_ll_1);
        linearLayout2 = (LinearLayout)findViewById(R.id.personal_ll_2);
        editText1 = (EditText)findViewById(R.id.personal_edit_1);
        editText2 = (EditText)findViewById(R.id.personal_edit_2);
        editText3 = (EditText)findViewById(R.id.personal_edit_3);
        checkBox0 = (CheckBox)findViewById(R.id.personal_checkbox_2);
        checkBox1 = (CheckBox)findViewById(R.id.personal_checkbox_1);

        imageView.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        checkBox0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkBox0.setChecked(true);
                    checkBox1.setChecked(false);
                }
            }
        });

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox0.setChecked(false);
                    checkBox1.setChecked(true);
                }
            }
        });

        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                SoftInputUtil softInputUtil = new SoftInputUtil();
                softInputUtil.hideSoftInput(Frag4List_Personal.this,v);

                if(hasFocus){
                    Log.d("ZXY","键盘");
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Frag4List_Personal.this,Frag4List_Personal.this,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();



                }else {

                }
            }
        });



        recyclerView = (RecyclerView)findViewById(R.id.personal_recycle_view);
        layoutManager = new LinearLayoutManager(this);//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        initEdit();
        initData();
        softwareAdapter = new SoftwareAdapter(this,softwareItemList);
        recyclerView.setAdapter(softwareAdapter);


    }


    private void initData(){
        softwareItemList.clear();
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        if(cursor.getString(cursor.getColumnIndex("id")).equals("")){
            addItem("ID","空");
        }else {
            addItem("ID",cursor.getString(cursor.getColumnIndex("id")));
        }
        if(cursor.getString(cursor.getColumnIndex("username")).equals("")){
            addItem("用户名","空");
        }else {
            addItem("用户名",cursor.getString(cursor.getColumnIndex("username")));
        }
        if(cursor.getString(cursor.getColumnIndex("gender")).equals("")){
            addItem("性别","空");
        }else {
            String genderTemp = cursor.getString(cursor.getColumnIndex("gender"));
            if (genderTemp.equals("0")){
                addItem("性别","女");
            }else if(genderTemp.equals("1")){
                addItem("性别","男");
            }
        }
        if(cursor.getString(cursor.getColumnIndex("personalSignature")).equals("")){
            addItem("个性签名","空");
        }else {
            addItem("个性签名",cursor.getString(cursor.getColumnIndex("personalSignature")));
        }
        if(cursor.getString(cursor.getColumnIndex("birthday")).equals("")){
            addItem("出生日期","空");
        }else {
            addItem("出生日期",cursor.getString(cursor.getColumnIndex("birthday")));
        }
        if(cursor.getString(cursor.getColumnIndex("phoneNumber")).equals("")){
            addItem("绑定手机","空");
        }else {
            Log.d("ZXY","非空");
            addItem("绑定手机",cursor.getString(cursor.getColumnIndex("phoneNumber")));
        }
        if(cursor.getString(cursor.getColumnIndex("email")).equals("")){
            addItem("绑定邮箱","空");
        }else {
            addItem("绑定邮箱",cursor.getString(cursor.getColumnIndex("email")));
        }
        cursor.close();

    }

    private void addItem(String string1,String string2){
        softwareItemList.add(new SoftwareItem(string1,string2));
    }

    private void initEdit(){
        softwareItemList.clear();
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        if(cursor.getString(cursor.getColumnIndex("username")).equals("")){
//            addItem("用户名","空");
        }else {
            editText1.setText(cursor.getString(cursor.getColumnIndex("username")));
        }
        if(cursor.getString(cursor.getColumnIndex("gender")).equals("")){
//            addItem("性别","空");
        }else {
            String genderTemp = cursor.getString(cursor.getColumnIndex("gender"));
            if (genderTemp.equals("0")){
                checkBox0.setChecked(true);
                checkBox1.setChecked(false);
//                addItem("性别","女");
            }else if(genderTemp.equals("1")){
                checkBox0.setChecked(false);
                checkBox1.setChecked(true);
//                addItem("性别","男");
            }
        }
        if(cursor.getString(cursor.getColumnIndex("personalSignature")).equals("")){
//            addItem("个性签名","空");
        }else {
            editText3.setText(cursor.getString(cursor.getColumnIndex("personalSignature")));
        }
        if(cursor.getString(cursor.getColumnIndex("birthday")).equals("")){
//            addItem("出生日期","空");
        }else {
            addItem("出生日期",cursor.getString(cursor.getColumnIndex("birthday")));
        }
        cursor.close();
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
                updateData();
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

    private void updateData(){
        String username = editText1.getText().toString();
        String birthDate = editText2.getText().toString();
        String personalSign = editText3.getText().toString();
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));

        if(checkBox0.isChecked()){
            dbOperator.Cud("update user_info set gender='0' where id='"+id+"'");
        }else {
            if(checkBox1.isChecked()){
                dbOperator.Cud("update user_info set gender='1' where id='"+id+"'");
            }
        }

        if(username.equals("") || username.equals(null)){

        }else {
            dbOperator.Cud("update user_info set username='"+username+"' where id='"+id+"'");
        }

        if(birthDate.equals("") || birthDate.equals(null)){

        }else {
            dbOperator.Cud("update user_info set birthday='"+birthDate+"' where id='"+id+"'");
        }

        if(personalSign.equals("") || personalSign.equals(null)){

        }else {
            dbOperator.Cud("update user_info set personalSignature='"+personalSign+"' where id='"+id+"'");
        }

        initData();
        recyclerView.setAdapter(softwareAdapter);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String accountingDate = String.format("%d%02d%02d",year,month+1,dayOfMonth);
        editText2.setText(accountingDate);
    }

}
