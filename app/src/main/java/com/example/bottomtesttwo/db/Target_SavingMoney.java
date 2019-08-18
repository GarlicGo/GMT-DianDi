package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

//存钱目标
public class Target_SavingMoney extends DataSupport {

    private long id;//
    private long targetHopeMoney;//存钱目标钱数
    private long targetTime;//存钱时长
    private long targetStart;//开始时间
    private long targetLastFrom;//上一次转出卡包对象
    private long targetLastTo;//上一次转入卡包对象

    //与其他表关联
    private List<Record_Saving> target_recordSavingList = new ArrayList<Record_Saving>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTargetHopeMoney() {
        return targetHopeMoney;
    }

    public void setTargetHopeMoney(long targetHopeMoney) {
        this.targetHopeMoney = targetHopeMoney;
    }

    public long getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(long targetTime) {
        this.targetTime = targetTime;
    }

    public long getTargetStart() {
        return targetStart;
    }

    public void setTargetStart(long targetStart) {
        this.targetStart = targetStart;
    }

    public long getTargetLastFrom() {
        return targetLastFrom;
    }

    public void setTargetLastFrom(long targetLastFrom) {
        this.targetLastFrom = targetLastFrom;
    }

    public long getTargetLastTo() {
        return targetLastTo;
    }

    public void setTargetLastTo(long targetLastTo) {
        this.targetLastTo = targetLastTo;
    }

    public List<Record_Saving> getTarget_recordSavingList() {
        return target_recordSavingList;
    }

    public void setTarget_recordSavingList(List<Record_Saving> target_recordSavingList) {
        this.target_recordSavingList = target_recordSavingList;
    }
}
