package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//卡户信息-存储用户的众多卡户（能存钱的）
public class Information_MoneyCard extends DataSupport {

    private long id;//
    private String userId;  //用户id
    private String mardContext;//卡户名称
    private String cardTip;//卡户介绍
    private String cardImg;//账号封面图片
    private long cardRestMoney;//余额

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

    public String getMardContext() {
        return mardContext;
    }

    public void setMardContext(String mardContext) {
        this.mardContext = mardContext;
    }

    public String getCardTip() {
        return cardTip;
    }

    public void setCardTip(String cardTip) {
        this.cardTip = cardTip;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public long getCardRestMoney() {
        return cardRestMoney;
    }

    public void setCardRestMoney(long cardRestMoney) {
        this.cardRestMoney = cardRestMoney;
    }
}
