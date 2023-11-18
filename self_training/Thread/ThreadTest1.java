package Thread;

public class ThreadTest1
{
    // main方法是由一条默认的主线程负责执行。
    public static void main(String[] args)
    {
        // 3、创建MyThread线程类的对象代表一个线程
        Thread t = new MyThread();
        // 4、启动线程（自动执行run方法的）
        t.start();
        for (int i = 1; i <= 5; i++)
        {
            System.out.println("主线程main输出：" + i);
        }
    }
}
