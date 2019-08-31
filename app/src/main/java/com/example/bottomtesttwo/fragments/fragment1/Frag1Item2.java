package com.example.bottomtesttwo.fragments.fragment1;

public class Frag1Item2 {

    private int imageAddress;
    private String name;
    private double moneyNumber;
    private long cardRecordsId;

    public Frag1Item2(long cardRecordsId,int imageAddress,String name,double moneyNumber){
        this.cardRecordsId = cardRecordsId;
        this.imageAddress = imageAddress;
        this.name = name;
        this.moneyNumber = moneyNumber;
    }

    public int getImageAddress() {
        return imageAddress;
    }

    public String getName() {
        return name;
    }

    public double getMoneyNumber() {
        return moneyNumber;
    }

    public long getCardRecordsId() {
        return cardRecordsId;
    }
}
