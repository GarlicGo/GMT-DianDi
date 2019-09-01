package com.example.bottomtesttwo.fragments.fragment2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment1.Frag1Item2Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;

import java.util.ArrayList;
import java.util.List;

public class TargetAdapter extends RecyclerView.Adapter{



    private Context mContext;
    private int mPosition;
    private List<TargetItem> targetItemList;
    private TargetAdapter.ClickInterface clickInterface;



    //回调接口
    public interface ClickInterface {
        void onItemClick(View view, int position);
    }

    public void setOnclick(TargetAdapter.ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        View clickView;

        public ViewHolder(@NonNull View view) {
            super(view);
            clickView = view;
            textView1 = (TextView)view.findViewById(R.id.frag2_target_name);
            textView2 = (TextView)view.findViewById(R.id.frag2_target_day);
            textView3 = (TextView)view.findViewById(R.id.frag2_target_money);
        }

    }

    public TargetAdapter(Context context, List<TargetItem> targetItems){
        mContext = context;
        targetItemList = targetItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.frag2_target_item,viewGroup,false);
        TargetAdapter.ViewHolder holder = new TargetAdapter.ViewHolder(view);

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

        TargetItem targetItem = targetItemList.get(position);
        ((ViewHolder)holder).textView1.setText(targetItem.getName());
        String temp1 = targetItem.getStartTime()+"";
        String temp2 = targetItem.getEndTime()+"";
        temp1 = temp1.substring(2,4)+"."+temp1.substring(4,6)+"."+temp1.substring(6);
        temp2 = temp2.substring(2,4)+"."+temp2.substring(4,6)+"."+temp2.substring(6);
        ((ViewHolder)holder).textView2.setText(temp1+" - "+temp2);
        String temp3 = String.format("%.2f",targetItem.getMoney());
        ((ViewHolder)holder).textView3.setText(""+temp3);

    }

    @Override
    public int getItemCount() {
        return targetItemList.size();
    }
}
