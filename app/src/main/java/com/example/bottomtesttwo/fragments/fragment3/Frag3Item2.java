package com.example.bottomtesttwo.fragments.fragment3;

public class Frag3Item2 {

    private int imageId;
    private String tip;
    private double number;

    public Frag3Item2(String tip,double number,int imageId){
        this.imageId = imageId;
        this.number =number;
        this.tip = tip;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTip() {
        return tip;
    }

    public double getNumber() {
        return number;
    }
}
