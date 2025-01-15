package day03.ATMSystem.ATM;

import lombok.Getter;

public class Account
{
    // 卡号的方法
    @Getter
    private String cardId; //卡号
    private String userName; //用户名
    //性别的方法
    @Getter
    private char sex; //性别
    //密码的方法
    @Getter
    private String passWord;//密码
    //余额的方法
    @Getter
    private double money; //余额
    //限额的方法
    @Getter
    private double limit; // 限额

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    //用户名的方法
    public String getUserName() {
        return userName + ( sex  == '男' ? "先生" : "女士");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}