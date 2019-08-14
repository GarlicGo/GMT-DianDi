package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//存钱目标
public class Target_SavingMoney extends DataSupport {
    private long id;//
    private String userId;  //用户id
    private long targetTime;//存钱时长
    private long timeStart;//开始时间
    private float targetMoney;//存钱目标
    private float savedMoney;//已存钱数

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(long targetTime) {
        this.targetTime = targetTime;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public float getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(long targetMoney) {
        this.targetMoney = targetMoney;
    }

    public float getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(long savedMoney) {
        this.savedMoney = savedMoney;
    }

    public void setTargetMoney(float targetMoney) {
        this.targetMoney = targetMoney;
    }

    public void setSavedMoney(float savedMoney) {
        this.savedMoney = savedMoney;
    }
}
