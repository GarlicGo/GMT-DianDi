package com.example.bottomtesttwo.fragments.fragment3.charts;

public class Analyze1 {

    private String date;
    private int money;
    public long dataTime;

    public Analyze1(String date,int money){
        this.date = date;
        this.money = money;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
