package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//卡户信息-存储用户的众多卡户（能存钱的）
public class Information_MoneyCard extends DataSupport {

    private long Id;//卡户ID
    private String moneyCardContext;//卡户名称
    private String moneyCardTip;//卡户介绍
    private String moneyCardImg;//账号封面图片
    private long moneyCardRestMoney;//余额

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getMoneyCardContext() {
        return moneyCardContext;
    }

    public void setMoneyCardContext(String moneyCardContext) {
        this.moneyCardContext = moneyCardContext;
    }

    public String getMoneyCardTip() {
        return moneyCardTip;
    }

    public void setMoneyCardTip(String moneyCardTip) {
        this.moneyCardTip = moneyCardTip;
    }

    public String getMoneyCardImg() {
        return moneyCardImg;
    }

    public void setMoneyCardImg(String moneyCardImg) {
        this.moneyCardImg = moneyCardImg;
    }

    public long getMoneyCardRestMoney() {
        return moneyCardRestMoney;
    }

    public void setMoneyCardRestMoney(long moneyCardRestMoney) {
        this.moneyCardRestMoney = moneyCardRestMoney;
    }
}
