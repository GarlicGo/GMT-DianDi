package com.example.bottomtesttwo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;
import com.example.bottomtesttwo.fragments.fragment3.charts.AnalyzeSpend;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item1;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item1Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item2;
import com.example.bottomtesttwo.fragments.fragment3.charts.DateIntent;
import com.example.bottomtesttwo.fragments.fragment3.charts.NumberIntent;

import java.util.ArrayList;
import java.util.List;

//记账页
public class Fragment3 extends Fragment implements View.OnClickListener {

    private View view;

    //中间跳转页面
    private ImageView analyzeSpendImg;
    private ImageView analyzeIncomeImg;
    private ImageView analyzeSumImg;
    private TextView analyzeSpendText;
    private TextView analyzeIncomeText;
    private TextView analyzeSumText;

    //RecyclerView
    List<Frag3Item> frag3ItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag3Adapter frag3Adapter;

    //顶部信息展示
    private double incomeMoney = 0;
    private double spendMoney = 0;
    private double money;
    TextView incomeTV ;
    TextView outcomeTV ;
    TextView totalTV ;


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
        analyzeSpendImg.setOnClickListener(this);
        analyzeIncomeImg.setOnClickListener(this);
        analyzeSumImg.setOnClickListener(this);
        analyzeSpendText.setOnClickListener(this);
        analyzeIncomeText.setOnClickListener(this);
        analyzeSumText.setOnClickListener(this);

        //RecyclerView
        initFrag3Item();
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment3_recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag3Adapter = new Frag3Adapter(getActivity(),frag3ItemList);
        recyclerView.setAdapter(frag3Adapter);

        //顶部信息展示
        incomeTV = (TextView) view.findViewById(R.id.t_income);
        outcomeTV = (TextView) view.findViewById(R.id.t_outcome);
        totalTV = (TextView) view.findViewById(R.id.t_total);
        incomeTV.setText(""+this.getIncomeMoney());
        outcomeTV.setText(""+this.getSpendMoney());
        totalTV.setText(""+this.getMoney());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.analyze_income_img:
            case R.id.analyze_income_text:
                initDate();
                initNumber();
                Intent intentIncome = new Intent(getActivity(), AnalyzeSpend.class);
                intentIncome.setAction("action");
                intentIncome.putExtra("dateIntent",dateIntent);
                intentIncome.putExtra("1","666");
                intentIncome.putExtra("numberIntent",numberIntent);
                startActivity(intentIncome);
                break;
            case R.id.analyze_spend_img:
            case R.id.analyze_spend_text:
                Intent intentSpend = new Intent(getActivity(), AnalyzeSpend.class);
                startActivity(intentSpend);
                break;
            case R.id.analyze_sum_img:
            case R.id.analyze_sum_text:
                Intent intentSum = new Intent(getActivity(), AnalyzeSpend.class);
                startActivity(intentSum);
                break;
                default:
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



    public void addFrag3Item(String tip,int imageId,double number,String date){

        //更新RecycrView
        frag3ItemList.add(0,new Frag3Item(tip,imageId,number,date));
        frag3Adapter = new Frag3Adapter(getActivity(),frag3ItemList);
        recyclerView.setAdapter(frag3Adapter);

        //更新顶部展示
        incomeTV.setText(""+this.getIncomeMoney());
        outcomeTV.setText(""+this.getSpendMoney());
        totalTV.setText(""+this.getMoney());
    }

    private void initFrag3Item(){

    }

}
