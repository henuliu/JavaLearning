package day03.ATMSystem.ATM;

public class Account
{
    private String cardId; //卡号
    private String userName; //用户名
    private char sex; //性别
    private String passWord;//密码
    private double money; //余额
    private double limit; // 限额

    //卡号的方法
    public String getCardId() {
        return cardId;
    }

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

    //性别的方法
    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    //密码的方法
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    //余额的方法
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    //限额的方法
    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}