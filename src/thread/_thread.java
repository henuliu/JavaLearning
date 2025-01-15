package thread;

/*
 * Java程序执行流的最小单位是线程。一个Java应用程序至少有一个线程，即 main线程,JVM还会启动一些守护线程，例如垃圾回收线程
 * 同一个进程内的线程共享内存空间、文件描述符等资源。多个线程可以并发执行
 * 线程的生命周期，Java线程有以下几种状态：
 * 1.New（新建）:线程对象被创建但尚未启动。
 * 2.Runnable（可运行）：线程正在JVM中执行，等待获取CPU时间片。
 * 3.Blocked（阻塞）：线程被阻塞，等待某个条件的发生（如锁、I/O操作完成等）。
 * 4.Waiting（等待）：线程在等待其他线程执行特定动作（如调用notify()或notifyAll()方法）
 * 5.Timed Waiting（计时等待）：线程在指定时间内等待（如调用sleep()、wait(long)等方法）。
 * 6.Terminated（终止）：线程执行完毕或因异常而终止。
 */
public class _thread
{
    // 程序都是从main方法启动的，java程序至少2个Thread线程，1个main线程 还有1个垃圾回收线程 回收没有应用指向的new出来的对象
    public static void main(String[] args)
    {
        SingerThread singerThread = new SingerThread();

//        singerThread.run(); // 注意线程启动不能run ，run就是普通方法调用了
        // 注意：
        // 1.线程启动得是start,这个新线程会去执行run()方法中的内容，线程能和别的线程竞争cpu时间片
        // 2.获取了cpu时间片，不会一次把代码执行完毕，而是执行一会，cpu还会切换到别的线程执行，一会再切回来，cpu时间片切换是有算法的最简单是轮询
        singerThread.start(); // 启动听歌线程  到这里 mainThread和singerThread 就会竞争cpu时间片了

        for (int i = 0; i < 100; i++)
        {
            System.out.println("--------------------------------------------我在看小说...." + i);
        }
    }
}


/**
 * 多线程实现方式有多种:
 * 1.实现 Runable接口
 * 2.继承 Thread类
 * 3.实现 Callable接口
 */
class SingerThread extends Thread
{

    // 实现run方法 线程切换过来时候就是执行run方法
    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println("我在听歌......." + i);
        }
    }
}