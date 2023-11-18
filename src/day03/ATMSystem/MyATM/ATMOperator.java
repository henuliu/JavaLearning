package day03.ATMSystem.MyATM;

import java.util.Scanner;

public class ATMOperator
{
    private Account[] Accounts;

    public ATMOperator(Account[] Accounts)
    {
        this.Accounts = Accounts;
    }

    //查询账户
    public void SearchAccount(String Account)
    {
        for(int i = 0; i< Accounts.length; i++)
        {
            if (Accounts[i]!=null)
                if(Accounts[i].getAccount().equals(Account))
                {
                    System.out.println("查询成功！当前账户名为:"+ Accounts[i].getAccount());
                    System.out.println("当前账户金额为:"+ Accounts[i].getMoney());
                    break;
                }
        }
    }
    //开户
    public void CreateAccount()
    {
        System.out.println("请您输入姓名");
        Scanner NameScanner=new Scanner(System.in);
        String Name=NameScanner.next();


    }

    //修改密码
    public void UpdatePassWord()
    {
        System.out.println("请输入您的账户名");
        Scanner UpdataScanner=new Scanner(System.in);
        String UpdataAccount=UpdataScanner.next();
        for(int i = 0; i< Accounts.length; i++)
        {
            if (Accounts[i]!=null)
                if (Accounts[i].getAccount().equals(UpdataAccount))
                {
                    System.out.println("请输入原密码:");
                    Scanner PriorScanner=new Scanner(System.in);
                    String PriorPassWord=UpdataScanner.next();
                    if (Accounts[i].getPassWord().equals(PriorPassWord))
                    {
                        System.out.println("原密码正确,请输入新密码:");
                        Scanner NewScanner=new Scanner(System.in);
                        String NewPassWord=NewScanner.next();
                        Accounts[i].setPassWord(NewPassWord);
                        System.out.println("修改成功");
                        break;
                    }
                    else
                    {
                        System.out.println("密码错误,请重试");
                        do
                        {
                            System.out.println("请输入原密码:");
                            Scanner ErrorPriorScanner=new Scanner(System.in);
                            String ErrorPriorPassWord=ErrorPriorScanner.next();
                            if (Accounts[i].getPassWord().equals(ErrorPriorPassWord))
                            {
                                System.out.println("原密码正确,请输入新密码:");
                                Scanner NewScanner=new Scanner(System.in);
                                String NewPassWord=NewScanner.next();
                                Accounts[i].setPassWord(NewPassWord);
                                System.out.println("修改成功");
                                break;
                            }
                        }while (Accounts[i].getPassWord().equals(PriorPassWord));
                    }
                }
        }
    }

    //存款
    public void DepositMoney(String Account)
    {
        int i;
        for(i=0; i< Accounts.length; i++)
        {
            if (Accounts[i]!=null)
                if(Accounts[i].getAccount().equals(Account))
                    System.out.println("当前账户金额为:"+ Accounts[i].getMoney());
            System.out.println("请输入您的存款金额");
            Scanner scanner=new Scanner(System.in);
            int num=scanner.nextInt();
            Accounts[i].setMoney(Accounts[i].getMoney()+num);
            System.out.println("存款成功,当前金额为"+ Accounts[i].getMoney());
            break;
        }
        if(i== Accounts.length)
        {
            System.out.println("找不到该账户,请重试");
        }
    }

    //取款
    public void DrawMoney(String Account)
    {
        int i=0;
        for(i=0; i< Accounts.length; i++)
        {
            if (Accounts[i]!=null)
                if(Accounts[i].getAccount().equals(Account))
                    System.out.println("当前账户金额为:"+ Accounts[i].getMoney());
            System.out.println("请输入您的取款金额");
            Scanner scanner=new Scanner(System.in);
            int num=scanner.nextInt();
            Accounts[i].setMoney(Accounts[i].getMoney()-num);
            if(Accounts[i].getMoney()-num>=0)
                System.out.println("取款成功,当前金额为"+ Accounts[i].getMoney());
            else
                System.out.println("余额不足,取款失败");
            break;
        }
        if(i== Accounts.length)
        {
            System.out.println("找不到该账户,请重试");
        }

    }

    //转账
    public void TransferMoney()
    {
        System.out.println("请输入您的账户名");
        Scanner GiveScanner=new Scanner(System.in);
        String GiveAccount=GiveScanner.next();

        int i,j;
        Account give=new Account();
        for(i=0; i< Accounts.length; i++)
        {
            if (Accounts[i]!=null)
                if(Accounts[i].getAccount().equals(GiveAccount))
                    System.out.println("当前账户金额为:"+ Accounts[i].getMoney());
            break;
        }

        System.out.println("请输入对方的账户名");
        Scanner GetScanner=new Scanner(System.in);
        String GetAccount =GetScanner.next();
        for(j=0; j< Accounts.length; j++)
            if (Accounts[j]!=null)
                if(Accounts[j].getAccount().equals(GetAccount))
                {
                    System.out.println("成功查询到对方账户,请输入转账金额:");
                    //当前账户的金额
                    int Money= Accounts[i].getMoney();

                    //设置转账金额
                    int num;
                    do
                    {
                        Scanner scanner=new Scanner(System.in);
                        num=scanner.nextInt();
                        if(Accounts[i].getMoney()-num>=0)
                        {
                            Accounts[i].setMoney(Accounts[i].getMoney()-num);
                            System.out.println("转账成功,您的账户当前金额为"+ Accounts[i].getMoney());
                            Accounts[j].setMoney(Accounts[j].getMoney()+num);
                        }
                        else
                        {
                            System.out.println("您的余额不足,转账失败,请重新输入");
                        }
                    }while (Money-num<0);

                    break;
                }

    }


}
