package com.example.bottomtesttwo.fragments.fragment4.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment2.TargetAdapter;

import java.util.List;

public class HeadAdapter extends RecyclerView.Adapter {



    private Context mContext;
    private int mPosition;
    private List<HeadItem> headItemList;
    private HeadAdapter.ClickInterface clickInterface;


    //回调接口
    public interface ClickInterface {
        void onItemClick(View view, int position);
    }

    public void setOnclick(HeadAdapter.ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    public HeadAdapter(List<HeadItem> headItemList) {
        this.headItemList = headItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardView = (CardView)view;
            textView = (TextView)view.findViewById(R.id.headImage_text);
            imageView = (ImageView) view.findViewById(R.id.headImage_img);
        }
    }

    public HeadAdapter(Context context, List<HeadItem> headItems){
        mContext = context;
        headItemList = headItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.head_image_item,viewGroup,false);

        HeadAdapter.ViewHolder holder = new HeadAdapter.ViewHolder(view);

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
        HeadItem headItem = headItemList.get(position);
        ((HeadAdapter.ViewHolder)holder).textView.setText(headItem.getText());
        Glide.with(mContext).load(headItem.getImageId()).into(((ViewHolder) holder).imageView);
//        ((HeadAdapter.ViewHolder)holder).imageView.setImageResource(headItem.getImageId());
    }

    @Override
    public int getItemCount() {
        return headItemList.size();
    }

}
