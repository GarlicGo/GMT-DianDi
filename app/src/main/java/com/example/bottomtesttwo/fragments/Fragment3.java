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
    private ImageView analyzeSpendImg;
    private ImageView analyzeIncomeImg;
    private ImageView analyzeSumImg;
    private TextView analyzeSpendText;
    private TextView analyzeIncomeText;
    private TextView analyzeSumText;

    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag3Item1Adapter frag3Item1Adapter;

    private double incomeMoney = 0;
    private double spendMoney = 0;
    private double money;
    TextView incomeTV ;
    TextView outcomeTV ;
    TextView totalTV ;



    public double getIncomeMoney() {
        incomeMoney = 0;
        for(Frag3Item1 item1:frag3Item1List){
            if(item1.getCost()>0){
                incomeMoney = incomeMoney + item1.getCost();
            }
        }
        incomeTV.setText(""+incomeMoney);
        return incomeMoney;
    }

    public double getSpendMoney() {
        spendMoney = 0;
        for (Frag3Item1 item1:frag3Item1List){
            if(item1.getCost()<0){
                spendMoney = spendMoney + item1.getCost();
            }
        }
        outcomeTV.setText(""+(-spendMoney));
        return -spendMoney;
    }

    public double getMoney() {
        money = 0;
        for (Frag3Item1 item1:frag3Item1List){
            money = money + item1.getCost();
        }
        totalTV.setText(""+money);
        return money;
    }

    public List<Frag3Item1> getFrag3Item1List() {
        return frag3Item1List;
    }

    public void setFrag3Item1List(List<Frag3Item1> frag3Item1List) {
        this.frag3Item1List = frag3Item1List;
    }

    //
    private List<Frag3Item1> frag3Item1List = new ArrayList<>();
//    private List<Frag3Item2> frag3Item2List = new ArrayList<>();

    private Button testButton1;

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

        incomeTV = (TextView) view.findViewById(R.id.t_income);
        outcomeTV = (TextView) view.findViewById(R.id.t_outcome);
        totalTV = (TextView) view.findViewById(R.id.t_total);


//        //滚动控件
//        initFrag3Item2();
//        initFrag3Item1();
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment3_recycle_view);
//
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
//
        frag3Item1Adapter = new Frag3Item1Adapter(getActivity(),frag3Item1List);
        recyclerView.setAdapter(frag3Item1Adapter);


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

    private void initFrag3Item1() {

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
//        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.frag3_tiem_recycleview);
//        recyclerView.setLayoutManager(layoutManager);

//        List<Frag3Item2> frag3Item2List = new ArrayList<>();
//        initFrag3Item2(frag3Item2List);
//
//        frag3Item1List.add(new Frag3Item1(66,"8月21日",frag3Item2List));

    }

//        frag3Item1List.add(new Frag3Item1("66","8月19日",frag3Item2List));
////        frag3Item1List.add(new Frag3Item1("66","08/19"));
//    }
//
    private void initFrag3Item2(List<Frag3Item2> frag3Item2List){
//        frag3Item2List.add(new Frag3Item2("洗漱",22,R.mipmap.app_logo));
//        frag3Item2List.add(new Frag3Item2("洗漱",22,R.mipmap.app_logo));
//        frag3Item2List.add(new Frag3Item2("洗漱",22,R.mipmap.app_logo));
    }

//    public void addFrag3Item1(String date,String tip,int number,int imageId){


    public void addFrag3Item1(String date,String tip,double number,int imageId){

        for(Frag3Item1 item1:frag3Item1List)
            if(item1.getDate().equals(date)){

                Toast.makeText(getActivity(),"true",Toast.LENGTH_SHORT).show();
                item1.addItem2(tip,number,imageId);
//                item1.setCost(item1.getCost()+number);
                item1.setCost(item1.getCost()+number);
                recyclerView.setAdapter(frag3Item1Adapter);

                getSpendMoney();
                getIncomeMoney();
                getMoney();

                return;

            }
//
//            Toast.makeText(getActivity(),"false",Toast.LENGTH_SHORT).show();

            List<Frag3Item2> item2List = new ArrayList<>();
            item2List.add(new Frag3Item2(tip,number,R.mipmap.income_lijin));
            frag3Item1List.add(new Frag3Item1(number,date,item2List));
            recyclerView.setAdapter(frag3Item1Adapter);
            getSpendMoney();
            getIncomeMoney();
            getMoney();

    }


}
