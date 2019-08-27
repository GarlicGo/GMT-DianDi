package com.example.bottomtesttwo.fragments.fragment4.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Adapter;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Item;

import java.util.List;

public class SoftwareAdapter extends RecyclerView.Adapter {



    private Context mContext;
    private int mPosition;
    private List<SoftwareItem> softwareItemList;



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull View view) {
            super(view);
            textView1 = (TextView)view.findViewById(R.id.frag4_list_software_item_1);
            textView2 = (TextView)view.findViewById(R.id.frag4_list_software_item_2);
        }
    }

    public SoftwareAdapter(Context context, List<SoftwareItem> softwareItems){
        mContext = context;
        softwareItemList = softwareItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.frag4_list_software_item,viewGroup,false);
        SoftwareAdapter.ViewHolder holder = new SoftwareAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SoftwareItem softwareItem = softwareItemList.get(position);
        ((ViewHolder)holder).textView1.setText(softwareItem.getString1());
        ((ViewHolder)holder).textView2.setText(softwareItem.getString2());
    }

    @Override
    public int getItemCount() {
        return softwareItemList.size();
    }
}
