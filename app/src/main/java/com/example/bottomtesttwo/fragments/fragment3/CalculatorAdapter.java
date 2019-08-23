package com.example.bottomtesttwo.fragments.fragment3;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.activity.MainActivity;

import java.util.List;

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.ViewHolder> {

    private List<CalculatorSortItem> calculatorSortItemList;
    public int intentPosition;
    public String intentText;

    //调用接口
//    private AdapterView.OnItemClickListener clickListener;
//
//    public void setClickListener(OnItemClickListener onItemClickListener) {
//        this.clickListener = (AdapterView.OnItemClickListener) onItemClickListener;
//    }
//
//    public static interface OnItemClickListener {
//        void onClick(int position);
//    }


//    public void setClickListener(AdapterView.OnItemClickListener clickListener) {
//        this.clickListener = clickListener;
//    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        View sortView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sortView = itemView;
            imageView = (ImageView)itemView.findViewById(R.id.calculator_item_iv);
            textView = (TextView)itemView.findViewById(R.id.calculator_item_tv);
//            itemView.setOnClickListener((View.OnClickListener) this);
        }

//        @Override
//        public void onClick(View v) {
//            if (clickListener != null) {
//                clickListener.onClick(getAdapterPosition());
//
//            }
//        }
    }

    public CalculatorAdapter(List<CalculatorSortItem> mcalculatorSortItemList){
        calculatorSortItemList = mcalculatorSortItemList;
    }


    @NonNull
    @Override
    public CalculatorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calculator_sort_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);


        holder.sortView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClick(v,holder.getAdapterPosition());


//                int posotion = holder.getAdapterPosition();
//                intentPosition = posotion;
//                CalculatorSortItem calculatorSortItem = calculatorSortItemList.get(posotion);
//                intentText = calculatorSortItem.getName();
//                Log.d("CalculatorAdapter","bowb");


//                holder.textView.setTextColor(Color.parseColor("#255bfc"));
//                holder.imageView.setColorFilter(Color.parseColor("#255bfc"));
//                Toast.makeText(v.getContext(),"you clicked "+calculatorSortItem.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalculatorAdapter.ViewHolder holder, int position) {
        CalculatorSortItem calculatorSortItem = calculatorSortItemList.get(position);
        holder.imageView.setImageResource(calculatorSortItem.getImageId());
        holder.textView.setText(calculatorSortItem.getName());
    }

    @Override
    public int getItemCount() {
        return calculatorSortItemList.size();
    }

    private onItemClick listener;

    public interface onItemClick{
        void onClick(View v,int i);
    }

    public void onClickListener(onItemClick listener){
        this.listener = listener;
    }
//    /**
//     * 在活动中实现的接口
//     */
//    public interface SelectItem {
//        /**
//         * 在活动中定义的方法
//         * @param view view对象
//         * @param position item的位置
//         */
//        void select(View view, int position);
//    }
//
//    public void setSelectItem(SelectItem selectItem) {
//        mSelectItem = selectItem;
//    }
}
