package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

//个人信息
public class Information_Personal extends DataSupport {

    private long personId;//用户ID
    private String userAccount;//账号
    private String characterPassword;//字符密码

    private String webIamge;//头像
    private String webName;//昵称
    private String personLabel;//个性签名
    private boolean gender;//性别：0-女；1-男；
    private long birthDay;//出生日期

    private String secretQuestion;//密保问题
    private String questionAnswer;//密保答案
    private String questionTip;//密保提示

    private String phoneNumber;//绑定手机
    private String emailAddress;//邮箱地址

    //表关联
    private List<Information_Account> accountCard_List = new ArrayList<Information_Account>();
    private List<Information_MoneyCard> moneyCard_List = new ArrayList<Information_MoneyCard>();
    private List<Records_Accounting> recordAccounting_List = new ArrayList<Records_Accounting>();
    private List<Target_SavingMoney> targetSaving_List = new ArrayList<Target_SavingMoney>();

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getCharacterPassword() {
        return characterPassword;
    }

    public void setCharacterPassword(String characterPassword) {
        this.characterPassword = characterPassword;
    }

    public String getWebIamge() {
        return webIamge;
    }

    public void setWebIamge(String webIamge) {
        this.webIamge = webIamge;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getPersonLabel() {
        return personLabel;
    }

    public void setPersonLabel(String personLabel) {
        this.personLabel = personLabel;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public long getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(long birthDay) {
        this.birthDay = birthDay;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionTip() {
        return questionTip;
    }

    public void setQuestionTip(String questionTip) {
        this.questionTip = questionTip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Information_Account> getAccountCard_List() {
        return accountCard_List;
    }

    public void setAccountCard_List(List<Information_Account> accountCard_List) {
        this.accountCard_List = accountCard_List;
    }

    public List<Information_MoneyCard> getMoneyCard_List() {
        return moneyCard_List;
    }

    public void setMoneyCard_List(List<Information_MoneyCard> moneyCard_List) {
        this.moneyCard_List = moneyCard_List;
    }

    public List<Records_Accounting> getRecordAccounting_List() {
        return recordAccounting_List;
    }

    public void setRecordAccounting_List(List<Records_Accounting> recordAccounting_List) {
        this.recordAccounting_List = recordAccounting_List;
    }

    public List<Target_SavingMoney> getTargetSaving_List() {
        return targetSaving_List;
    }

    public void setTargetSaving_List(List<Target_SavingMoney> targetSaving_List) {
        this.targetSaving_List = targetSaving_List;
    }
}
