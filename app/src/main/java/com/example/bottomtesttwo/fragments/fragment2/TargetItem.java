package com.example.bottomtesttwo.fragments.fragment2;

import com.bumptech.glide.manager.TargetTracker;

public class TargetItem {

    private long id;
    private double money;
    private int startTime;
    private int endTime;
    private String name;

    public TargetItem(String name,double money,int startTime,int endTime){
        this.name = name;
        this.money = money;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int successTime) {
        this.endTime = successTime;
    }
}
