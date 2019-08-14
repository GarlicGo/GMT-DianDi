package com.example.bottomtesttwo.db;

import org.litepal.crud.DataSupport;

//个人信息
public class Information_Personal extends DataSupport {

    private long id;//用户ID
    private String webName;//昵称
    private String personLabel;//个性签名
    private boolean gender;//性别：0-女；1-男；

    private long birthDay;//出生日期

    private String charactersPassword;//字符密码

    private String secretQuestion;//密保问题
    private String questionAnswer;//密保答案
    private String questionTip;//密保提示

    private String phoneNumber;//绑定手机
    private String emailAddress;//邮箱地址


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCharactersPassword() {
        return charactersPassword;
    }

    public void setCharactersPassword(String charactersPassword) {
        this.charactersPassword = charactersPassword;
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
}
