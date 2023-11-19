package Thread;

public class MyThread extends Thread
{
    // 2、必须重写Thread类的run方法
    @Override
    public void run()
    {
        // 描述线程的执行任务。
        for (int i = 1; i <= 10; i++)
        {
            System.out.println("子线程MyThread输出：" + i);
        }
    }
}
