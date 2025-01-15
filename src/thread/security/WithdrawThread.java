package thread.security;

/**
 * 取钱线程
 */
public class WithdrawThread extends Thread
{
    private Account account;

    public WithdrawThread(Account account, String name)
    {
        super(name);
        this.account = account;
    }


    @Override
    public void run()
    {
        // 取钱(小明，小红)
        account.withdrawMoneyByLock(100000);
    }
}