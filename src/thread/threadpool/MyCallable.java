package thread.threadpool;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>
{
    private int number;

    public MyCallable(int number)
    {
        this.number = number;
    }

    // 2.重写call方法
    @Override
    public String call()
    {
        // 描述线程的任务，返回线程执行后得到的结果。
        // 需求：求1~n的和并返回。
        int sum = 0;
        for (int i = 1; i <= number; i++)
        {
            sum += i;
        }
        return Thread.currentThread().getName() + "求出了1-" + number + "的和是：" + sum;
    }
}