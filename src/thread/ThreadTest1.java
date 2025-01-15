package thread;

public class ThreadTest1
{
    public static void main(String[] args)
    {
        Thread t1 = new MyThread();
        t1.setName("MyThread"); // 设置线程名称;
        t1.start();
        System.out.println(t1.getName());  // MyThread

        Thread t2 = new MyThread("2号线程");
        t2.start();
        System.out.println(t2.getName()); // 2号线程

        // 主线程对象的名字
        // 哪个线程执行它，它就会得到哪个线程对象。
        Thread currentThread = Thread.currentThread();
        currentThread.setName("最牛的线程");
        System.out.println(currentThread.getName()); // main

        for (int i = 1; i <= 5; i++)
        {
            System.out.println(currentThread.getName() + "线程输出：" + i);
        }
    }
}
