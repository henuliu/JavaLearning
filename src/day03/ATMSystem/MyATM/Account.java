package day03.ATMSystem.MyATM;

public class Account
{
    //银行卡号(由系统生成)
    private String Account;

    //银行密码
    private  String PassWord;

    //账户金额
    private int Money;

    //姓名
    private String Name;

    //性别
    private char Sex;

    //取现额度
    private int Amount;

    // TODO
    public Account()
    {

    }

    public Account(String Account, String PassWord, int Money)
    {
        this.Account=Account;
        this.PassWord=PassWord;
        this.Money=Money;
    }

    public void setAccount(String Account)
    {
        this.Account=Account;
    }

    public String getAccount()
    {
        return Account;
    }

    public void setPassWord(String PassWord)
    {
        this.PassWord=PassWord;
    }
    public String getPassWord()
    {
        return PassWord;
    }

    public void setMoney(int Money)
    {
        this.Money=Money;
    }

    public int getMoney()
    {
        return Money;
    }

}
