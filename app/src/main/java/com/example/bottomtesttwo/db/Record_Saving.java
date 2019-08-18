package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//存钱记录
public class Record_Saving extends DataSupport {

    private long id;//
    private String recordSavingRecordTip;//存钱备注
    private long recordSavingDate;//存钱日期
    private float recordSavingMoneyNumber;//存钱金额
    private long recordSavingTo;//转入类别
    private long recordSavingFrom;//转出类别

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecordSavingRecordTip() {
        return recordSavingRecordTip;
    }

    public void setRecordSavingRecordTip(String recordSavingRecordTip) {
        this.recordSavingRecordTip = recordSavingRecordTip;
    }

    public long getRecordSavingDate() {
        return recordSavingDate;
    }

    public void setRecordSavingDate(long recordSavingDate) {
        this.recordSavingDate = recordSavingDate;
    }

    public float getRecordSavingMoneyNumber() {
        return recordSavingMoneyNumber;
    }

    public void setRecordSavingMoneyNumber(float recordSavingMoneyNumber) {
        this.recordSavingMoneyNumber = recordSavingMoneyNumber;
    }

    public long getRecordSavingTo() {
        return recordSavingTo;
    }

    public void setRecordSavingTo(long recordSavingTo) {
        this.recordSavingTo = recordSavingTo;
    }

    public long getRecordSavingFrom() {
        return recordSavingFrom;
    }

    public void setRecordSavingFrom(long recordSavingFrom) {
        this.recordSavingFrom = recordSavingFrom;
    }
}
