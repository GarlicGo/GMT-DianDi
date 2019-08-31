package com.example.bottomtesttwo.fragments;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.db.Record_Saving;
import com.example.bottomtesttwo.fragments.fragment1.Calculator11Update;
import com.example.bottomtesttwo.fragments.fragment1.Calculator12Update;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1Adapter;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item2;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item2Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Adapter;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Email;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Personal;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Phone;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Secret;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Software;
import com.example.bottomtesttwo.fragments.login.LoginActivity;
import com.example.bottomtesttwo.serverd.DBOperator;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

//卡包页
public class Fragment1 extends Fragment implements View.OnClickListener{

    private View view;
    private TextView textView1;
    private TextView textView2;
    private TextView textNumber1;
    private TextView textNumber2;
    private Button but;

    private boolean frag1FirstChange = true;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    private int accountNumber = 0;
    private int cardNumber = 0;
    private boolean init = false;

    //RecyclerView
    List<Frag1Item1> frag1Item1List_1 = new ArrayList<>();
    List<Frag1Item2> frag1Item1List_2 = new ArrayList<>();
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag1Item1Adapter frag1Item1Adapter_1;
    Frag1Item2Adapter frag1Item1Adapter_2;

    public Fragment1() {
    }


    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        textView1 = (TextView)view.findViewById(R.id.frag4_text_1);
        textView2 = (TextView)view.findViewById(R.id.frag4_text_2);
        textNumber1 = (TextView)view.findViewById(R.id.frag1_num_1);
        textNumber2 = (TextView)view.findViewById(R.id.frag1_num_2);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);

        recyclerView = (RecyclerView)view.findViewById(R.id.frag1_recycle);
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag1Item1Adapter_1 = new Frag1Item1Adapter(getActivity(),frag1Item1List_1);
        frag1Item1Adapter_2 = new Frag1Item2Adapter(getActivity(),frag1Item1List_2);
        recyclerView.setAdapter(frag1Item1Adapter_1);

        //RecyclerView
        if(init == false){
//            initItem1List();
            initItem2List();
            initItem1List();
            init = true;
        }

        textNumber1.setText(frag1Item1List_1.size()+"");
        textNumber2.setText(frag1Item1List_2.size()+"");

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                frag1Item1Adapter_1.setOnclick(new Frag1Item1Adapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Frag1Item1 frag1Item1 = frag1Item1List_1.get(position);
                        Intent intent = new Intent(MainActivity.instance, Calculator11Update.class);
                        intent.putExtra("account_records_id",frag1Item1.getAccountCardId());
                        getActivity().startActivityForResult(intent,20);
                    }
                });
            }
        });

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                frag1Item1Adapter_2.setOnclick(new Frag1Item2Adapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Frag1Item2 frag1Item2 = frag1Item1List_2.get(position);
                        Intent intent = new Intent(MainActivity.instance, Calculator12Update.class);
                        intent.putExtra("card_records_id",frag1Item2.getCardRecordsId());
                        getActivity().startActivityForResult(intent,21);
                    }
                });
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag4_text_1:
                textView1.setBackground(this.getResources().getDrawable(R.drawable.shape_checked));
                textView1.setTextColor(this.getResources().getColor(R.color.textWhite));
                textView2.setBackground(this.getResources().getDrawable(R.drawable.shape));
                textView2.setTextColor(this.getResources().getColor(R.color.gray));
                recyclerView.setAdapter(frag1Item1Adapter_1);
//                MainActivity.instance.addBottom.setVisibility(View.VISIBLE);
                frag1FirstChange = true;
                break;
            case R.id.frag4_text_2:
                textView1.setBackground(this.getResources().getDrawable(R.drawable.shape));
                textView1.setTextColor(this.getResources().getColor(R.color.gray));
                textView2.setBackground(this.getResources().getDrawable(R.drawable.shape_checked));
                textView2.setTextColor(this.getResources().getColor(R.color.textWhite));
                recyclerView.setAdapter(frag1Item1Adapter_2);
//                MainActivity.instance.addBottom.setVisibility(View.GONE);
                frag1FirstChange = false;
                break;
        }
    }

    public void initItem1List(){
        frag1Item1List_1.clear();
        Log.d("ZXYFrag1","成功调用initItemList");
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        Log.d("ZXYFrag1","user_info_id :"+id);
        cursor = dbOperator.Query( "select * from account_records where user_info_id='"+id+"'");
//        cursor = dbOperator.Query( "select * from account_records");
        if(cursor.moveToFirst()){
            Log.d("ZXYFrag1","成功move to fist");
            do{
                Log.d("ZXYFrag1","成功do");
                int count = 0;
                String name = cursor.getString(cursor.getColumnIndex("title"));
                String tip = cursor.getString(cursor.getColumnIndex("description"));

                long accountCardId = cursor.getLong(cursor.getColumnIndex("id"));
                //账号
                String accountNumber2 = cursor.getString(cursor.getColumnIndex("accountNumber"));
                //用户名
                String username = cursor.getString(cursor.getColumnIndex("username"));
                //密码
                String password = cursor.getString(cursor.getColumnIndex("password"));
                //绑定邮箱
                String email = cursor.getString(cursor.getColumnIndex("email"));
                //绑定手机
                String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                //身份证
                String IDNumber = cursor.getString(cursor.getColumnIndex("IDNumber"));

                String name1 = "";
                String password1 = "";
                String name2 = "";
                String password2 = "";

                List<String> stringList = new ArrayList<>();
                stringList.add(accountNumber2);
                stringList.add(username);
                stringList.add(password);
                stringList.add(email);
                stringList.add(phoneNumber);
                stringList.add(IDNumber);
//                Log.d("ZXYSTR","stringList.size():"+stringList.size());
                for(String str:stringList){
                    int posotion = stringList.indexOf(str);
//                    Log.d("ZXYSTR","posotion:"+posotion+":"+str);
                    if(str.equals("")){

                    }else {
                        count++;
                        if(count == 1){
                            switch (posotion){
                                case 0:
                                    name1 = "账号";
                                    password1 = accountNumber2;
                                    break;
                                case 1:
                                    name1 = "用户名";
                                    password1 = username;
                                    break;
                                case 2:
                                    name1 = "密码";
                                    password1 = password;
                                    break;
                                case 3:
                                    name1 = "绑定邮箱";
                                    password1 = email;
                                    break;
                                case 4:
                                    name1 = "绑定手机";
                                    password1 = phoneNumber;
                                    break;
                                case 5:
                                    name1 = "绑定身份证";
                                    password1 = IDNumber;
                                    break;
                            }
                        }else if (count == 2){
                            switch (posotion){
                                case 0:
                                    name2 = "账号";
                                    password2 = accountNumber2;
                                    break;
                                case 1:
                                    name2 = "用户名";
                                    password2 = username;
                                    break;
                                case 2:
                                    name2 = "密码";
                                    password2 = password;
                                    break;
                                case 3:
                                    name2 = "绑定邮箱";
                                    password2 = email;
                                    break;
                                case 4:
                                    name2 = "绑定手机";
                                    password2 = phoneNumber;
                                    break;
                                case 5:
                                    name2 = "绑定身份证";
                                    password2 = IDNumber;
                                    break;
                            }
                        }
                    }
                }
//                Log.d("ZXYSTR","count :"+count);
                frag1Item1List_1.add(0,new Frag1Item1(accountCardId,name,tip,name1,
                        password1,name2,password2,count));
                accountNumber++;
            }while (cursor.moveToNext());
        }else {
            Log.d("ZXYFrag1","没有 move to first");
        }
        cursor.close();
        recyclerView.setAdapter(frag1Item1Adapter_1);
        textNumber1.setText(frag1Item1List_1.size()+"");
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

        recyclerView.setAdapter(frag1Item1Adapter_2);
        textNumber2.setText(frag1Item1List_2.size()+"");
    }

    public boolean isFrag1FirstChange() {
        return frag1FirstChange;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("ZXYBACKK","did");
//        switch (requestCode){
//            case 1:
//                if(resultCode == RESULT_OK){
//                    initItem1List();
//                    recyclerView.setAdapter(frag1Item1Adapter_1);
//                }
//                break;
//            case 2:
//                if(resultCode == RESULT_OK){
//                    initItem2List();
//                    recyclerView.setAdapter(frag1Item1Adapter_2);
//                }
//                break;
//        }
//    }


}
