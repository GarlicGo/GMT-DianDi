package com.example.bottomtesttwo.fragments.fragment4;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bottomtesttwo.R;

import java.util.List;

public class Frag4Adapter extends RecyclerView.Adapter {


    private Context mContext;
    private int mPosition;
    private List<Frag4Item> mFrag4ItemList;
    private ClickInterface clickInterface;

    //回调接口
    public interface ClickInterface {
//        void onButtonClick(View view, int position);
        void onItemClick(View view, int position);
    }

    public void setOnclick(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View clickView;
        TextView frag4Tip;

        public ViewHolder(@NonNull View view) {
            super(view);
            clickView = view;
            frag4Tip = (TextView)view.findViewById(R.id.frag4_text);
        }

    }

    public Frag4Adapter(Context context, List<Frag4Item> frag4ItemList){
        mContext = context;
        mFrag4ItemList = frag4ItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment4_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = holder.getAdapterPosition();
                clickInterface.onItemClick(v,mPosition);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Frag4Item frag4Item = mFrag4ItemList.get(position);
        ((ViewHolder)holder).frag4Tip.setText(frag4Item.getTip());
    }

    @Override
    public int getItemCount() {
        return mFrag4ItemList.size();
    }

//    // 外部接口回调监听
//    public interface OnItemClickListener {
//        void onClick(int pos);
//    }
}
