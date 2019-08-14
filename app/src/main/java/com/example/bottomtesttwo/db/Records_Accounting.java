package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//记账记录
public class Records_Accounting extends DataSupport {

    private long id;
    private String userId;  //用户id
    private boolean inOrOut;//操作类型：支出/收入
    private int accountOperationType;//记账类型：1-50支出类别；51-100收入类别；
    private String payImg;//记账类型图片
    private String accountRecordTip;//记账备注
    private int typeMoneyCard;//卡包操作对象
    private long accountDate;//记账日期
    private float accountMoneyNumber;//记账金额


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

    public boolean isInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(boolean inOrOut) {
        this.inOrOut = inOrOut;
    }

    public int getAccountOperationType() {
        return accountOperationType;
    }

    public void setAccountOperationType(int accountOperationType) {
        this.accountOperationType = accountOperationType;
    }

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg;
    }

    public String getAccountRecordTip() {
        return accountRecordTip;
    }

    public void setAccountRecordTip(String accountRecordTip) {
        this.accountRecordTip = accountRecordTip;
    }

    public int getTypeMoneyCard() {
        return typeMoneyCard;
    }

    public void setTypeMoneyCard(int typeMoneyCard) {
        this.typeMoneyCard = typeMoneyCard;
    }

    public long getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(long accountDate) {
        this.accountDate = accountDate;
    }

    public float getAccountMoneyNumber() {
        return accountMoneyNumber;
    }

    public void setAccountMoneyNumber(float accountMoneyNumber) {
        this.accountMoneyNumber = accountMoneyNumber;
    }


}
