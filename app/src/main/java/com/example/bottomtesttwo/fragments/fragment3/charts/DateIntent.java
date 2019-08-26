package com.example.bottomtesttwo.fragments.fragment3.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DateIntent implements Serializable {

    List<String> stringListIntent = new ArrayList<>();

    public void add(String xTipString){
        stringListIntent.add(xTipString);
    }

    public List<String> getStringListIntent() {
        return stringListIntent;
    }
}
