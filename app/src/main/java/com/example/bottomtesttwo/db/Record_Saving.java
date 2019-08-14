package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//存钱记录
public class Record_Saving extends DataSupport {

    private long id;
    private String userId;  //用户id
    private String savingRecordTip;//存钱备注
    private int savingTypeMoneyCard;//卡包操作对象
    private String cardImg;//卡包操作对象图片
    private long savingDate;//存钱日期
    private float savingMoneyNumber;//存钱金额


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

    public String getSavingRecordTip() {
        return savingRecordTip;
    }

    public void setSavingRecordTip(String savingRecordTip) {
        this.savingRecordTip = savingRecordTip;
    }

    public int getSavingTypeMoneyCard() {
        return savingTypeMoneyCard;
    }

    public void setSavingTypeMoneyCard(int savingTypeMoneyCard) {
        this.savingTypeMoneyCard = savingTypeMoneyCard;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public long getSavingDate() {
        return savingDate;
    }

    public void setSavingDate(long savingDate) {
        this.savingDate = savingDate;
    }

    public float getSavingMoneyNumber() {
        return savingMoneyNumber;
    }

    public void setSavingMoneyNumber(float savingMoneyNumber) {
        this.savingMoneyNumber = savingMoneyNumber;
    }
}
