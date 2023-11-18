package day03.ATMSystem.MyATM;


import java.util.Scanner;

public class ATMSystemTest
{
    //查询当前账户
    public static void main(String[] args)
    {

        Account[]Users=new Account[5];
        Account User1=new Account("张三","123456",1000);
        Users[0]=User1;
        Users[1]=new Account("李四","123456",1000);
        ATMOperator operator=new ATMOperator(Users);
        System.out.println("====欢迎来到ATM系统===");
        while (true)
        {
            System.out.println("1.查询账户");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退出系统");
            System.out.println("请输入您的选项");

            Scanner scanner=new Scanner(System.in);
            int option=scanner.nextInt();

            switch (option)
            {
                case 1:
                    System.out.println("请输入账户名:");
                    Scanner SearchScanner=new Scanner(System.in);
                    String account1=SearchScanner.next();
                    operator.SearchAccount(account1);
                    break;
                case 2:
                    System.out.println("请输入您的账户名");
                    Scanner DepositScanner=new Scanner(System.in);
                    String account2=DepositScanner.next();
                    operator.DepositMoney(account2);
                    break;
                case 3:
                    System.out.println("请输入您的账户名");
                    Scanner DrawScanner=new Scanner(System.in);
                    String account3=DrawScanner.next();
                    operator.DrawMoney(account3);
                    break;
                case 4:
                    operator.TransferMoney();
                    break;
                case 5:
                    operator.UpdatePassWord();
                    break;
                case 6:
                    System.out.println("退出成功!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("你选择的操作无效，重新选择!");
                    break;
            }

        }


    }
}
