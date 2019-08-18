package com.example.bottomtesttwo.fragments;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.AnalyzeSpend;

import org.litepal.tablemanager.Connector;

//记账页
public class Fragment3 extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView analyzeSpendImg;
    private ImageView analyzeIncomeImg;
    private ImageView analyzeSumImg;
    private TextView analyzeSpendText;
    private TextView analyzeIncomeText;
    private TextView analyzeSumText;

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
        analyzeSpendImg = (ImageView)view.findViewById(R.id.analyze_spend_img);
        analyzeIncomeImg = (ImageView)view.findViewById(R.id.analyze_income_img);
        analyzeSumImg = (ImageView)view.findViewById(R.id.analyze_sum_img);
        analyzeSpendText = (TextView)view.findViewById(R.id.analyze_spend_text);
        analyzeIncomeText = (TextView)view.findViewById(R.id.analyze_income_text);
        analyzeSumText = (TextView)view.findViewById(R.id.analyze_sum_text);

        testButton1 = (Button)view.findViewById(R.id.test_button1);
        testButton1.setOnClickListener(this);

        analyzeSpendImg.setOnClickListener(this);
        analyzeIncomeImg.setOnClickListener(this);
        analyzeSumImg.setOnClickListener(this);
        analyzeSpendText.setOnClickListener(this);
        analyzeIncomeText.setOnClickListener(this);
        analyzeSumText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.analyze_income_img:
            case R.id.analyze_income_text:
                Intent intentIncome = new Intent(getActivity(), AnalyzeSpend.class);
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
            case R.id.test_button1:
                Connector.getDatabase();

                default:
                    break;

        }
    }
}
