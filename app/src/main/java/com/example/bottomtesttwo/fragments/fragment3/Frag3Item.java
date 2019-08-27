package com.example.bottomtesttwo.fragments.fragment3;

public class Frag3Item {

    private int imageId;
    private String tip;
    private double number;
    private String date;

    public Frag3Item(String tip,int imageId,double number,String date){
        this.imageId = imageId;
        this.tip = tip;
        this.number = number;
        this.date = date;
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

    public String getDate() {
        return date;
    }
}
