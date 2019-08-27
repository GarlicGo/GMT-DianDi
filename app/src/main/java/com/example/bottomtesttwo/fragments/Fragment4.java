package com.example.bottomtesttwo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Adapter;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Item;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Email;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Personal;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Phone;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Question;
import com.example.bottomtesttwo.fragments.fragment4.Frag4List_Software;

import java.util.ArrayList;
import java.util.List;

//我页
public class Fragment4 extends Fragment {

    private View view;
    private boolean firstDo = true;

    //RecyclerView
    List<Frag4Item> frag4ItemList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Frag4Adapter frag4Adapter;

    public Fragment4() {
    }


    public static Fragment4 newInstance() {
        Fragment4 fragment = new Fragment4();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_4, container, false);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(firstDo){
            initDate();
            firstDo = false;
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.frag4_recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());//指定布局方式（线性布局）
        recyclerView.setLayoutManager(layoutManager);
        frag4Adapter = new Frag4Adapter(getActivity(),frag4ItemList);
        recyclerView.setAdapter(frag4Adapter);

        //添加点击事件
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                frag4Adapter.setOnclick(new Frag4Adapter.ClickInterface() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Log.d("ZXY",position+"");
                        switch (position){
                            case 0:
                                Intent intent0 = new Intent(getActivity(), Frag4List_Personal.class);
                                startActivity(intent0);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getActivity(), Frag4List_Question.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(getActivity(), Frag4List_Phone.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(getActivity(), Frag4List_Email.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                Intent intent4 = new Intent(getActivity(),Frag4List_Software.class);
                                startActivity(intent4);
                                break;
                                default:
                                    break;
                        }
                    }
                });
            }
        });
    }



    private void initDate(){
        frag4ItemList.add(new Frag4Item("个人信息"));
        frag4ItemList.add(new Frag4Item("修改密码"));
        frag4ItemList.add(new Frag4Item("手机绑定"));
        frag4ItemList.add(new Frag4Item("邮箱绑定"));
        frag4ItemList.add(new Frag4Item("关于软件"));
    }

}
