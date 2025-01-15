package thread.security;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account
{
    private String cardId; // 卡号
    private double money; // 余额。

    public Account()
    {
    }

    public Account(String cardId, double money)
    {
        this.cardId = cardId;
        this.money = money;
    }

    // 1.同步代码块
    public void withdrawMoneyBySynchronized(double money)
    {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();
        // 1、判断余额是否足够
        // synchronized (锁对象),锁对象：必须是一个唯一的对象（同一个地址）,this正好代表共享资源！
        synchronized (this)
        {
            // 访问共享数据的代码
            if (this.money >= money)
            {
                System.out.println(name + "来取钱" + money + "成功！");
                this.money -= money;
                System.out.println(name + "来取钱后，余额剩余：" + this.money);
            } else
            {
                System.out.println(name + "来取钱：余额不足~");
            }
        }
    }

    // 2.同步方法
    public synchronized void withdrawMoneyByMethod(double money)
    {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();
        // 1、判断余额是否足够
        if (this.money >= money)
        {
            System.out.println(name + "来取钱" + money + "成功！");
            this.money -= money;
            System.out.println(name + "来取钱后，余额剩余：" + this.money);
        } else
        {
            System.out.println(name + "来取钱：余额不足~");
        }
    }

    // Lock锁
    // 创建一个锁对象
    private final Lock lk = new ReentrantLock();

    public void withdrawMoneyByLock(double money)
    {
        // 先搞清楚是谁来取钱？
        String name = Thread.currentThread().getName();
        try
        {
            lk.lock(); // 加锁
            // 1、判断余额是否足够
            if (this.money >= money)
            {
                System.out.println(name + "来取钱" + money + "成功！");
                this.money -= money;
                System.out.println(name + "来取钱后，余额剩余：" + this.money);
            } else
            {
                System.out.println(name + "来取钱：余额不足~");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            lk.unlock(); // 解锁
        }
    }

    public String getCardId()
    {
        return cardId;
    }

    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }

    public double getMoney()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }
}