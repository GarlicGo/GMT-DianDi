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

public class Frag3Item2Adapter extends RecyclerView.Adapter {


    private Context mContext;
    private int mPosition;
    private List<Frag3Item2> mFrag3Item2List;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item2Image;
        TextView item2Text1;
        TextView item2Text2;


        public ViewHolder(@NonNull View view) {
            super(view);
            item2Image = (ImageView)view.findViewById(R.id.frag3_item2_iv);
            item2Text1 = (TextView)view.findViewById(R.id.frag3_item2_tv1);
            item2Text2 = (TextView)view.findViewById(R.id.frag3_item2_tv2);
//            //这里设置一个tag,作为被嵌套的recycleview item点击事件的 position
//            lay_option.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mList.get((int) v.getTag()).setSelect(true);
//                    ((MainActivity) mContext).OnClickListener(mPosition, (int) v.getTag());
//                }
//            });
        }
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public Frag3Item2Adapter(Context context,List<Frag3Item2> frag3Item2List,int position){
        mContext = context;
        mFrag3Item2List = frag3Item2List;
        mPosition = position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment3_item1_item2,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Frag3Item2 frag3Item2 = mFrag3Item2List.get(position);
        ((ViewHolder)holder).item2Image.setImageResource(frag3Item2.getImageId());
        ((ViewHolder)holder).item2Text1.setText(frag3Item2.getTip());
        ((ViewHolder)holder).item2Text2.setText("¥ "+ frag3Item2.getNumber());
//        ((ViewHolder)holder).item2Text2.setText("¥ "+ frag3Item2.getNumber());
    }


    @Override
    public int getItemCount() {
        return mFrag3Item2List.size();
    }
}
