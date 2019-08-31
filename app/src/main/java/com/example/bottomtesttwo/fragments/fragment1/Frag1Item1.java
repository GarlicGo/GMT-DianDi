package com.example.bottomtesttwo.fragments.fragment1;

public class Frag1Item1 {

    private String name;
    private String tip;
    private String name1;
    private String password1;
    private String name2;
    private String password2;
    private int allNumber;
    private long accountCardId;

    public Frag1Item1(long accountCardId,String name,String tip,String name1,String password1,String name2,String password2,int
                      allNumber){
        this.name = name;
        this.tip = tip;
        this.name1 = name1;
        this.password1 = password1;
        this.name2 = name2;
        this.password2 = password2;
        this.allNumber = allNumber;
        this.accountCardId = accountCardId;
    }

    public int getAllNumber() {
        return allNumber;
    }

    public String getName() {
        return name;
    }

    public String getTip() {
        return tip;
    }

    public String getName1() {
        return name1;
    }

    public String getPassword1() {
        return password1;
    }

    public String getName2() {
        return name2;
    }

    public String getPassword2() {
        return password2;
    }

    public long getAccountCardId() {
        return accountCardId;
    }
}
