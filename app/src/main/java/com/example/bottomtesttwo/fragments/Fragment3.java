package com.example.bottomtesttwo.fragments;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;
import com.example.bottomtesttwo.fragments.fragment3.charts.AnalyzeAll;
import com.example.bottomtesttwo.fragments.fragment3.charts.AnalyzeIncome;
import com.example.bottomtesttwo.fragments.fragment3.charts.AnalyzeSpend;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item1;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item1Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item2;
import com.example.bottomtesttwo.fragments.fragment3.charts.DateIntent;
import com.example.bottomtesttwo.fragments.fragment3.charts.NumberIntent;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Personal;
import com.example.bottomtesttwo.serverd.DBOperator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//记账页
public class Fragment3 extends Fragment implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    private View view;

    //中间跳转页面
    private ImageView analyzeSpendImg;
    private ImageView analyzeIncomeImg;
    private ImageView analyzeSumImg;
    private TextView analyzeSpendText;
    private TextView analyzeIncomeText;
    private TextView analyzeSumText;
    public TextView textDate;
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH)+1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    public String frag3Date = String.format("%02d%02d00",year%100,month%100);

    DBOperator dbOperator = DBOperator.getOperator();
    Cursor cursor;
    private int id ;

    //RecyclerView
    List<Frag3Item> frag3ItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag3Adapter frag3Adapter;

    //顶部信息展示
    private double incomeMoney = 0;
    private double spendMoney = 0;
    private double money;
    TextView incomeTV;
    TextView outcomeTV;
    TextView totalTV;


    public Fragment3() {
    }

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //中间三个图表分析按钮的点击事件
        analyzeSpendImg = (ImageView)view.findViewById(R.id.analyze_spend_img);
        analyzeIncomeImg = (ImageView)view.findViewById(R.id.analyze_income_img);
        analyzeSumImg = (ImageView)view.findViewById(R.id.analyze_sum_img);
        analyzeSpendText = (TextView)view.findViewById(R.id.analyze_spend_text);
        analyzeIncomeText = (TextView)view.findViewById(R.id.analyze_income_text);
        analyzeSumText = (TextView)view.findViewById(R.id.analyze_sum_text);
        textDate = (TextView)view.findViewById(R.id.frag3_date);
        analyzeSpendImg.setOnClickListener(this);
        analyzeIncomeImg.setOnClickListener(this);
        analyzeSumImg.setOnClickListener(this);
        analyzeSpendText.setOnClickListener(this);
        analyzeIncomeText.setOnClickListener(this);
        analyzeSumText.setOnClickListener(this);
        textDate.setOnClickListener(this);

        //顶部信息展示
        incomeTV = (TextView) view.findViewById(R.id.t_income);
        outcomeTV = (TextView) view.findViewById(R.id.t_outcome);
        totalTV = (TextView) view.findViewById(R.id.t_total);
        //RecyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment3_recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag3Adapter = new Frag3Adapter(getActivity(),frag3ItemList);
        initFrag3Item();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.analyze_income_img:
            case R.id.analyze_income_text:
//                initDate();
//                initNumber();
                Intent intentIncome = new Intent(getActivity(), AnalyzeIncome.class);
                intentIncome.putExtra("date",frag3Date);
                startActivity(intentIncome);
                break;
            case R.id.analyze_spend_img:
            case R.id.analyze_spend_text:
                Intent intentSpend = new Intent(getActivity(), AnalyzeSpend.class);
                intentSpend.putExtra("date",frag3Date);
                startActivity(intentSpend);
                break;
            case R.id.analyze_sum_img:
            case R.id.analyze_sum_text:
                Intent intentAll = new Intent(getActivity(), AnalyzeAll.class);
                intentAll.putExtra("date",frag3Date);
                startActivity(intentAll);
                break;
                default:
                    break;
            case R.id.frag3_date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.instance,MainActivity.instance,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                DatePicker dp = findDatePicker((ViewGroup) datePickerDialog.getWindow().getDecorView());
                if (dp != null) {
                    ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
                }
                break;

        }
    }


    public double getIncomeMoney() {
        incomeMoney = 0;
        for(Frag3Item item1:frag3ItemList){
            if(item1.getNumber()>0){
                incomeMoney = incomeMoney + item1.getNumber();
            }
        }
        //incomeTV.setText(""+incomeMoney);
        return incomeMoney;
    }

    public double getSpendMoney() {
        spendMoney = 0;
        for (Frag3Item item1:frag3ItemList){
            if(item1.getNumber()<0){
                spendMoney = spendMoney + item1.getNumber();
            }
        }
        //outcomeTV.setText(""+(-spendMoney));
        return -spendMoney;
    }

    public double getMoney() {
        money = 0;
        for (Frag3Item item1:frag3ItemList){
            money = money + item1.getNumber();
        }
        //totalTV.setText(""+money);
        return money;
    }

    NumberIntent numberIntent = new NumberIntent();
    DateIntent dateIntent = new DateIntent();

    public void initDate(){
        dateIntent.add("6");
        dateIntent.add("7");
        dateIntent.add("8");
        dateIntent.add("9");
        dateIntent.add("10");
    }

    public void initNumber(){
        numberIntent.add(16);
        numberIntent.add(17);
        numberIntent.add(18);
        numberIntent.add(19);
        numberIntent.add(20);
    }



    public void addFrag3Item(String tip,int imageId,double number,long date){

        //更新RecycrView
        frag3ItemList.add(0,new Frag3Item(tip,imageId,number,date));
        frag3Adapter = new Frag3Adapter(getActivity(),frag3ItemList);
        recyclerView.setAdapter(frag3Adapter);

        //更新顶部展示
        incomeTV.setText(""+this.getIncomeMoney());
        outcomeTV.setText(""+this.getSpendMoney());
        totalTV.setText(""+this.getMoney());
    }

    public void initFrag3Item(){

        double in = 0;
        double out = 0;
        double all = 0;
        frag3ItemList.clear();
        Log.d("ZXYFrag1","成功调用initItemList");
        cursor = dbOperator.Query( "select * from user_info");
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("id"));
        Log.d("ZXYFrag1","user_info_id :"+id);
        cursor = dbOperator.Query( "select * from amount_changes where user_info_id='"+id+"'");

        if(cursor.moveToFirst()){
            do{
                Log.d("FRAG3TOG","success into movetofirst");
                long amount_changes_id = cursor.getLong(cursor.getColumnIndex("id"));
                int icon = cursor.getInt(cursor.getColumnIndex("icon"));
                String remarks = cursor.getString(cursor.getColumnIndex("remarks"));
                double changeAmount = cursor.getDouble(cursor.getColumnIndex("changeAmount"));
                long date = cursor.getLong(cursor.getColumnIndex("date"));

                long frag3DateChange = Long.valueOf(frag3Date);

                if(frag3DateChange < date && date<(frag3DateChange+100)){

                }else {
                    continue;
                }

                if(changeAmount>=0){
                    in = in + changeAmount;
                }else {
                    out = out + changeAmount;
                }
                all = all + changeAmount;

                Frag3Item frag3ItemTemp = new Frag3Item(remarks,icon,changeAmount,date);
                frag3ItemTemp.setAmount_changes_id(amount_changes_id);
                frag3ItemList.add(frag3ItemTemp);
            }while (cursor.moveToNext());

//            frag3ItemList.sort((Frag3Item m1,Frag3Item m2)-> m1.getDate().compareTo());
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

            //遍历之后的顶部显示
            incomeTV.setText(""+in+"");
            outcomeTV.setText(""+out+"");
            totalTV.setText(""+all+"");
            recyclerView.setAdapter(frag3Adapter);
        }else {
            Log.d("FRAG3TOG","failed into movetofirst");
        }
    }

    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        frag3Date = String.format("%02d%02d00",year%100,(month+1)%100);
        Toast.makeText(getActivity(),"11",Toast.LENGTH_SHORT).show();
        initFrag3Item();
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//        textDate.setText(String.format("%d年%d月",year,month+1));
//        Toast.makeText(getActivity(),"hhh",Toast.LENGTH_SHORT).show();
////        dp_date = String.format("%d月%d日",month+1,dayOfMonth);
////        accountingDate = String.format("%d年%d月%d日",year,month+1,dayOfMonth);
////        tv_date.setText(dp_date);
//    }

}
