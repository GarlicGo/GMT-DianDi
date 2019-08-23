package com.example.bottomtesttwo.fragments.fragment3;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Frag3Item1Adapter extends RecyclerView.Adapter {

    private List<Frag3Item1> mFrag3Item1List;
    private boolean one = true;
    private Context mContext;
    Frag3Item2Adapter frag3Item2Adapter;
//    frag3Item2Adapter = new Frag3Item2Adapter(frag3Item2List);

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView item1Text1;
        TextView item1Text2;
        RecyclerView recyclerView;

        private Frag3Item2Adapter frag3Item2Adapter;
        private List<Frag3Item2> frag3Item2List = new ArrayList<>();


        public ViewHolder(@NonNull View view) {
            super(view);
            item1Text1 = (TextView)view.findViewById(R.id.frag3_item1_tv1);
            item1Text2 = (TextView)view.findViewById(R.id.frag3_item1_tv2);
            recyclerView = (RecyclerView)view.findViewById(R.id.frag3_tiem_recycleview);
        }
    }

    public Frag3Item1Adapter(Context context,List<Frag3Item1> frag3Item1List){
        mContext = context;
        mFrag3Item1List = frag3Item1List;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment3_item1,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);

//        holder.initFrag3Item1_Item2List();
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Frag3Item1 frag3Item1 = mFrag3Item1List.get(position);
        ((ViewHolder)holder).item1Text1.setText(frag3Item1.getDate());
        ((ViewHolder)holder).item1Text2.setText("¥ "+ frag3Item1.getCost());

//        if(((ViewHolder)holder).frag3Item2Adapter == null){
            ((ViewHolder)holder).frag3Item2Adapter = new Frag3Item2Adapter(mContext,frag3Item1.getFrag3Item1Item2List(),position);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);//指定布局方式（线性布局）
            ((ViewHolder)holder).recyclerView.setLayoutManager(layoutManager);
            ((ViewHolder)holder).recyclerView.setAdapter(((ViewHolder)holder).frag3Item2Adapter);

//        }else {
//            ((ViewHolder)holder).frag3Item2Adapter.setPosition(position);
//            ((ViewHolder)holder).frag3Item2Adapter.notifyDataSetChanged();
//        }


//        if(one){
//            one = false;
//
//        }


    }

    @Override
    public int getItemCount() {
        return mFrag3Item1List.size();
    }




}
