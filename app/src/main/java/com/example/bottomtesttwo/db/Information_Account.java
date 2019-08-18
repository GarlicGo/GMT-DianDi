package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//账号本信息-存储用户的众多账号
public class Information_Account extends DataSupport {

    private long id;//用户ID
    private String accountName;//账号名称
    private String accountTip;//账号介绍
    private String accountImage;//账号封面图片

    private String accountNumber;//账号
    private String acoountPassword;//密码
    private String accountWebName;//用户名
    private String accountPhoneNumber;//绑定手机
    private String accountEmailAdress;//绑定邮箱
    private String accountIdCard;//绑定身份证

    private String Tip_accountOwnDesign_1;//自定义字段(字段内容)
    private String Tip_accountOwnDesign_2;
    private String Tip_accountOwnDesign_3;
    private String Tip_accountOwnDesign_4;

    private String accountOwnDesign_1;//自定义(具体内容)
    private String accountOwnDesign_2;
    private String accountOwnDesign_3;
    private String accountOwnDesign_4;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountTip() {
        return accountTip;
    }

    public void setAccountTip(String accountTip) {
        this.accountTip = accountTip;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAcoountPassword() {
        return acoountPassword;
    }

    public void setAcoountPassword(String acoountPassword) {
        this.acoountPassword = acoountPassword;
    }

    public String getAccountWebName() {
        return accountWebName;
    }

    public void setAccountWebName(String accountWebName) {
        this.accountWebName = accountWebName;
    }

    public String getAccountPhoneNumber() {
        return accountPhoneNumber;
    }

    public void setAccountPhoneNumber(String accountPhoneNumber) {
        this.accountPhoneNumber = accountPhoneNumber;
    }

    public String getAccountEmailAdress() {
        return accountEmailAdress;
    }

    public void setAccountEmailAdress(String accountEmailAdress) {
        this.accountEmailAdress = accountEmailAdress;
    }

    public String getAccountIdCard() {
        return accountIdCard;
    }

    public void setAccountIdCard(String accountIdCard) {
        this.accountIdCard = accountIdCard;
    }

    public String getTip_accountOwnDesign_1() {
        return Tip_accountOwnDesign_1;
    }

    public void setTip_accountOwnDesign_1(String tip_accountOwnDesign_1) {
        Tip_accountOwnDesign_1 = tip_accountOwnDesign_1;
    }

    public String getTip_accountOwnDesign_2() {
        return Tip_accountOwnDesign_2;
    }

    public void setTip_accountOwnDesign_2(String tip_accountOwnDesign_2) {
        Tip_accountOwnDesign_2 = tip_accountOwnDesign_2;
    }

    public String getTip_accountOwnDesign_3() {
        return Tip_accountOwnDesign_3;
    }

    public void setTip_accountOwnDesign_3(String tip_accountOwnDesign_3) {
        Tip_accountOwnDesign_3 = tip_accountOwnDesign_3;
    }

    public String getTip_accountOwnDesign_4() {
        return Tip_accountOwnDesign_4;
    }

    public void setTip_accountOwnDesign_4(String tip_accountOwnDesign_4) {
        Tip_accountOwnDesign_4 = tip_accountOwnDesign_4;
    }

    public String getAccountOwnDesign_1() {
        return accountOwnDesign_1;
    }

    public void setAccountOwnDesign_1(String accountOwnDesign_1) {
        this.accountOwnDesign_1 = accountOwnDesign_1;
    }

    public String getAccountOwnDesign_2() {
        return accountOwnDesign_2;
    }

    public void setAccountOwnDesign_2(String accountOwnDesign_2) {
        this.accountOwnDesign_2 = accountOwnDesign_2;
    }

    public String getAccountOwnDesign_3() {
        return accountOwnDesign_3;
    }

    public void setAccountOwnDesign_3(String accountOwnDesign_3) {
        this.accountOwnDesign_3 = accountOwnDesign_3;
    }

    public String getAccountOwnDesign_4() {
        return accountOwnDesign_4;
    }

    public void setAccountOwnDesign_4(String accountOwnDesign_4) {
        this.accountOwnDesign_4 = accountOwnDesign_4;
    }
}
