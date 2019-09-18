package com.example.bottomtesttwo.fragments.fragment3;

import com.example.bottomtesttwo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Frag3Item1 {

    private double cost;
    private String date;

    private List<Frag3Item2> frag3Item1Item2List = new ArrayList<>();

    public Frag3Item1(double cost,String data,List<Frag3Item2> frag3Item1Item2List){
        this.cost = cost;
        this.date = data;
        this.frag3Item1Item2List = frag3Item1Item2List;
    }

//    public Frag3Item1(String cost,String data){
//        this.cost = cost;
//        this.date = data;
//    }

    public void addItem2(String tip,double number,int imageId){
        frag3Item1Item2List.add(new Frag3Item2(tip,number,imageId));
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public List<Frag3Item2> getFrag3Item1Item2List() {
        return frag3Item1Item2List;
    }



}
