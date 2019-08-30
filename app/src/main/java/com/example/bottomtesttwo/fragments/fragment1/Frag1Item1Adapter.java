package com.example.bottomtesttwo.fragments.fragment1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.fragments.Fragment1;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Adapter;
import com.example.bottomtesttwo.fragments.fragment3.Frag3Item;
import com.example.bottomtesttwo.fragments.fragment4.Frag4Adapter;

import java.util.List;

public class Frag1Item1Adapter extends RecyclerView.Adapter {

    private Context mContext;
    private int mPosition;
    private List<Frag1Item1> frag1Item1List;

    private Frag1Item1Adapter.ClickInterface clickInterface;


    //回调接口
    public interface ClickInterface {
        void onItemClick(View view, int position);
    }

    public void setOnclick(Frag1Item1Adapter.ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewTip;
        TextView textViewName1;
        TextView textViewPassword1;
        TextView textViewName2;
        TextView textViewPassword2;
        TextView textViewNumber;
        View clickView;

        public ViewHolder(@NonNull View view) {
            super(view);
            clickView = view;
            textViewName = (TextView)view.findViewById(R.id.frag1_item1_name);
            textViewTip = (TextView)view.findViewById(R.id.frag1_item1_tip);
            textViewName1 = (TextView)view.findViewById(R.id.frag1_item1_name_1);
            textViewPassword1 = (TextView)view.findViewById(R.id.frag1_item1_password_1);
            textViewName2 = (TextView)view.findViewById(R.id.frag1_item1_name_2);
            textViewPassword2 = (TextView)view.findViewById(R.id.frag1_item1_password_2);
            textViewNumber = (TextView)view.findViewById(R.id.frag1_item_allnumber);
        }

    }

    public Frag1Item1Adapter(Context context, List<Frag1Item1> frag1Item1s){
        mContext = context;
        frag1Item1List = frag1Item1s;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.frag1_item1,viewGroup,false);
        Frag1Item1Adapter.ViewHolder holder = new Frag1Item1Adapter.ViewHolder(view);

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
        Frag1Item1 frag1Item1 = frag1Item1List.get(position);


        ((ViewHolder)holder).textViewName.setText(frag1Item1.getName());
        ((ViewHolder)holder).textViewTip.setText(frag1Item1.getTip());
        ((ViewHolder)holder).textViewName1.setText(frag1Item1.getName1());
        ((ViewHolder)holder).textViewPassword1.setText(frag1Item1.getPassword1());
        ((ViewHolder)holder).textViewName2.setText(frag1Item1.getName2());
        ((ViewHolder)holder).textViewPassword2.setText(frag1Item1.getPassword2());
        ((ViewHolder)holder).textViewNumber.setText(""+frag1Item1.getAllNumber());
    }

    @Override
    public int getItemCount() {
        return frag1Item1List.size();
    }

}
