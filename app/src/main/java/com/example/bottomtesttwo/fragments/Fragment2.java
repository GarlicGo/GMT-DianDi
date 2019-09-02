package com.example.bottomtesttwo.fragments;


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
import android.widget.TextView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.fragment1.Calculator11Update;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item1Adapter;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item2;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;
import com.example.bottomtesttwo.serverd.DBOperator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//存钱页
public class Fragment2 extends Fragment {

//    private View view;
    private View view;
    //RecyclerView
    List<Frag3Item> frag3ItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag3Adapter frag3Adapter;
    TextView textView2;
    TextView textView1;
    TextView textTop;
    TextView textBottom;

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;
    private int startDay = 0;
    private double allMoney = 0;
    private int passDay;
    private double passMoney;

    public Fragment2() {
    }


    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_2, container, false);
        view = inflater.inflate(R.layout.fragment_2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //RecyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment2_recycle_view);
        textView2 = (TextView)view.findViewById(R.id.b33423432);
        textView1 = (TextView)view.findViewById(R.id.b32423);
        textTop = (TextView)view.findViewById(R.id.sssaaaa);
        textBottom = (TextView)view.findViewById(R.id.ss3343432);
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag3Adapter = new Frag3Adapter(getActivity(),frag3ItemList);
        init();

    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//    }
//    private void calculator2_onClick1(View view){
//        switch (view.getId()){
//            case R.id.frag2_2_2:
//                break;
//        }
//    }
    public void init(){
        passMoney = 0;
        frag3ItemList.clear();
        String naname;
        int enddDate;
        Log.d("ZXYFrag1","成功调用initItemList");
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        Log.d("ZXYFrag1","user_info_id :"+id);
        cursor = dbOperator.Query( "select * from plan_info where user_info_id='"+id+"' and originCard='1'");

        if(cursor.moveToFirst()) {

            String plan_info_id = cursor.getString(cursor.getColumnIndex("id"));
            startDay = cursor.getInt(cursor.getColumnIndex("startTime"));
            allMoney = cursor.getDouble(cursor.getColumnIndex("expectantAmount"));
            enddDate = cursor.getInt(cursor.getColumnIndex("endTime"));
            naname = cursor.getString(cursor.getColumnIndex("title"));
            cursor.close();
            cursor = dbOperator.Query( "select * from saving_records where user_info_id='"+id+"' and plan_info_id='"+plan_info_id+"'");
            if(cursor.moveToFirst()){
                do {

                    String tip = cursor.getString(cursor.getColumnIndex("remarks"));
                    double number = cursor.getDouble(cursor.getColumnIndex("savingAmount"));
                    long date = cursor.getLong(cursor.getColumnIndex("date"));
                    Frag3Item frag3Item = new Frag3Item(tip,R.mipmap.income_lijin,number,date);
                    long id = cursor.getLong(cursor.getColumnIndex("id"));
                    frag3Item.setAmount_changes_id(id);
                    frag3ItemList.add(frag3Item);

                    passMoney = passMoney + number;

                }while (cursor.moveToNext());
                Collections.sort(frag3ItemList, new Comparator<Frag3Item>() {
                    @Override
                    public int compare(Frag3Item o1, Frag3Item o2) {
                        if (o1.getDate() - o2.getDate() < 0) { //变成 < 可以变成递减排序
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });

                Calendar calendar = Calendar.getInstance();
                //获取系统时间
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String nowDate = String.format("%02d%02d%02d",year%100,(month)%100,day%100);
                int intNowDate = Integer.parseInt(nowDate);
                startDay = startDay%1000000;
                enddDate = enddDate%1000000;
                Log.d("FGDADF","nowData:"+intNowDate+"  startDate:"+startDay +" endDate:"+enddDate);
                int a = 0;
                int dateCha = (intNowDate - startDay) + 1;
                if(intNowDate>enddDate){
                    a=-1;
                }else {
                    if(dateCha<0){
                        a = -2;
                    }else {
                        if(dateCha/1000>0){
                            a = (dateCha/1000)*365 + a;
                        }
                        if((dateCha%1000)/100>0){
                            a = ((dateCha%1000)/100)*30 + a;
                        }
                        if ((dateCha%100)>0){
                            a = (dateCha%100) + a;
                        }
                    }
                }
                Log.d("FGDADF","datacha:"+dateCha);
                Log.d("FGDADF","a:"+a);


                textView2.setText("已存"+String.format("%.2f",passMoney)+"元");
                textTop.setText(naname);
                if(a==-1){
                    textView1.setText("已过时间");
                }else if(a==-2){
                    textView1.setText("还未开始");
                }
                else {
                    textView1.setText("已存"+a+"天");
                }

                textBottom.setText(""+String.format("今日推荐：%.2f",allMoney/(enddDate-startDay))+"元");

            }else {

            }

            recyclerView.setAdapter(frag3Adapter);
        }
    }


}
