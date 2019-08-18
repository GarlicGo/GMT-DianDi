package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//记账记录
public class Records_Accounting extends DataSupport {

    private long id;//
    private long recordFirstType;//大类别（支出/收入/转账；默认为支出）
    private long recordSecondType;//小类别（快递/餐饮/...）
    private String recordSecondTypeImage;//小类别对应图片
    private long recordAccountingDate;//记账日期
    private String recordAccountintTip;//记账备注
    private float recordAccountMoneyNumber;//记账金额
    private long recordAccountingMoneyCard;//卡包操作对象

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecordFirstType() {
        return recordFirstType;
    }

    public void setRecordFirstType(long recordFirstType) {
        this.recordFirstType = recordFirstType;
    }

    public long getRecordSecondType() {
        return recordSecondType;
    }

    public void setRecordSecondType(long recordSecondType) {
        this.recordSecondType = recordSecondType;
    }

    public String getRecordSecondTypeImage() {
        return recordSecondTypeImage;
    }

    public void setRecordSecondTypeImage(String recordSecondTypeImage) {
        this.recordSecondTypeImage = recordSecondTypeImage;
    }

    public long getRecordAccountingDate() {
        return recordAccountingDate;
    }

    public void setRecordAccountingDate(long recordAccountingDate) {
        this.recordAccountingDate = recordAccountingDate;
    }

    public String getRecordAccountintTip() {
        return recordAccountintTip;
    }

    public void setRecordAccountintTip(String recordAccountintTip) {
        this.recordAccountintTip = recordAccountintTip;
    }

    public float getRecordAccountMoneyNumber() {
        return recordAccountMoneyNumber;
    }

    public void setRecordAccountMoneyNumber(float recordAccountMoneyNumber) {
        this.recordAccountMoneyNumber = recordAccountMoneyNumber;
    }

    public long getRecordAccountingMoneyCard() {
        return recordAccountingMoneyCard;
    }

    public void setRecordAccountingMoneyCard(long recordAccountingMoneyCard) {
        this.recordAccountingMoneyCard = recordAccountingMoneyCard;
    }
}
