package thread.threadpool;

/**
 * 线程任务类
 */
public class MyRunnable implements Runnable
{
    @Override
    public void run()
    {
        // 任务是干啥的？
        System.out.println(Thread.currentThread().getName() + " ==> 输出666~~");
        //为了模拟线程一直在执行，这里睡久一点
        try
        {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}