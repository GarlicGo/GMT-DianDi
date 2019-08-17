package com.example.bottomtesttwo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomtesttwo.R;

//我页
public class Fragment5 extends Fragment {


    public Fragment5() {
    }


    public static Fragment5 newInstance() {
        Fragment5 fragment = new Fragment5();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_5, container, false);
    }

}
