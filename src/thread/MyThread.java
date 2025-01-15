package thread;

public class MyThread extends Thread
{

    public MyThread()
    {
    }

    public MyThread(String name)
    {
        // 1.执行父类Thread(String name)构造器，为当前线程设置名字
        super(name);
    }

    @Override
    public void run()
    {
        // 2.currentThread() 哪个线程执行它，它就会得到哪个线程对象。
        Thread t = Thread.currentThread();
        for (int i = 1; i <= 3; i++)
        {
            //3.getName() 获取线程名称
            System.out.println(t.getName() + "输出：" + i);
        }
    }
}
