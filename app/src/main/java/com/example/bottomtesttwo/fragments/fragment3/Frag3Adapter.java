package com.example.bottomtesttwo.fragments.fragment3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomtesttwo.R;

import java.util.List;

public class Frag3Adapter extends RecyclerView.Adapter {


    private Context mContext;
    private int mPosition;
    private List<Frag3Item> mFrag3ItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView frag3ItemImg;
        TextView frag3ItemDate;
        TextView frag3ItemTip;
        TextView frag3ItemMoney;


        public ViewHolder(@NonNull View view) {
            super(view);
            frag3ItemImg = (ImageView)view.findViewById(R.id.frag3_item_image);
            frag3ItemDate = (TextView)view.findViewById(R.id.frag3_item_date);
            frag3ItemTip = (TextView)view.findViewById(R.id.frag3_item_tip);
            frag3ItemMoney = (TextView)view.findViewById(R.id.frag3_item_money);
        }

    }

    public Frag3Adapter(Context context, List<Frag3Item> frag3ItemList){
        mContext = context;
        mFrag3ItemList = frag3ItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment3_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Frag3Item frag3Item = mFrag3ItemList.get(position);
        ((ViewHolder)holder).frag3ItemImg.setImageResource(frag3Item.getImageId());
        ((ViewHolder)holder).frag3ItemDate.setText(frag3Item.getDate());
        ((ViewHolder)holder).frag3ItemTip.setText(frag3Item.getTip());
        ((ViewHolder)holder).frag3ItemMoney.setText("¥ "+frag3Item.getNumber());
    }

    @Override
    public int getItemCount() {
        return mFrag3ItemList.size();
    }
}
