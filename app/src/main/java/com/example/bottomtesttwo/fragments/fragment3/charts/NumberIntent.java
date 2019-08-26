package com.example.bottomtesttwo.fragments.fragment3.charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NumberIntent implements Serializable {

    List<Integer> intListIntent = new ArrayList<>();

    public void add(int number){
        intListIntent.add(number);
    }

    public List<Integer> getIntListIntent() {
        return intListIntent;
    }
}
