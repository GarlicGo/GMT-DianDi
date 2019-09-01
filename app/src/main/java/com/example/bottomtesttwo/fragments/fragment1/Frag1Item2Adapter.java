package com.example.bottomtesttwo.fragments.fragment1;

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

public class Frag1Item2Adapter extends RecyclerView.Adapter {

    private Context mContext;
    private int mPosition;
    private List<Frag1Item2> frag1Item2List;

    private Frag1Item2Adapter.ClickInterface clickInterface;
    //回调接口
    public interface ClickInterface {
        void onItemClick(View view, int position);
    }
    public void setOnclick(Frag1Item2Adapter.ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        View clickView;

        public ViewHolder(@NonNull View view) {
            super(view);
            clickView = view;
            imageView = (ImageView)view.findViewById(R.id.frag1_cal2_iv);
            textView1 = (TextView)view.findViewById(R.id.frag1_cal2_tv1);
            textView2 = (TextView)view.findViewById(R.id.frag1_cal2_tv2);
        }

    }

    public Frag1Item2Adapter(Context context, List<Frag1Item2> frag1Item2s){
        mContext = context;
        frag1Item2List = frag1Item2s;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.frag1_item2,viewGroup,false);
        Frag1Item2Adapter.ViewHolder holder = new Frag1Item2Adapter.ViewHolder(view);

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
        Frag1Item2 frag1Item2 = frag1Item2List.get(position);
        ((ViewHolder)holder).imageView.setImageResource(frag1Item2.getImageAddress());
        ((ViewHolder)holder).textView1.setText(frag1Item2.getName());
        String temp = String.format("%.2f",frag1Item2.getMoneyNumber());
        ((ViewHolder)holder).textView2.setText(temp);
    }

    @Override
    public int getItemCount() {
        return frag1Item2List.size();
    }
}
