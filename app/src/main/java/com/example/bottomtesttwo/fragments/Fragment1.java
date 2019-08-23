package com.example.bottomtesttwo.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bottomtesttwo.activity.MainActivity;
import com.example.bottomtesttwo.R;
import com.example.bottomtesttwo.db.Record_Saving;

import org.litepal.crud.DataSupport;

//卡包页
public class Fragment1 extends Fragment {

    private View view;
    private Button but;

    public Fragment1() {
    }


    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        but = (Button)view.findViewById(R.id.frag1_button1);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity activity = (MainActivity)getActivity();
//                activity.replaceFragment(new Fragment5());
                Record_Saving record_saving = DataSupport.find(Record_Saving.class,1);
                if(record_saving == null)
                    but.setText("null");
            }
        });
    }
}
