package com.example.bottomtesttwo.fragments.fragment3;

public class Frag3Item {

    private int imageId;
    private String tip;
    private double number;
    private long date;

    private long amount_changes_id;

    public Frag3Item(String tip,int imageId,double number,long date){
        this.imageId = imageId;
        this.tip = tip;
        this.number = number;
        this.date = date;
    }

    public long getAmount_changes_id() {
        return amount_changes_id;
    }

    public void setAmount_changes_id(long amount_changes_id) {
        this.amount_changes_id = amount_changes_id;
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

    public long getDate() {
        return date;
    }
}
