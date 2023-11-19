package 多线程;


/**
 *  多线程是 重难点 非常高级重要的实用的技术
 *  并发和并行
 *      并行的意思是 有多核cpu，每个cpu执行一段程序，确实同时执行 并行
 *      并发的意思是 有1核cpu， 一会执行A程序 ， 一会执行B程序 ， 来回切换，只是由于cpu太快 ，人类感觉是同时 ， 并发
 *
 *  先入个门: 用程序实现同时听歌和看小说 ？ 怎么同时，打印交叉
 */
public class demo_01 {

    // 程序都是从main方法启动的，java程序至少2个Thread线程，1个main线程 还有1个垃圾回收线程 回收没有应用指向的new出来的对象
    public static void main(String[] args) {


        SingerThread singerThread = new SingerThread();

//        singerThread.run(); // 注意线程启动不能run ，run就是普通方法调用了

        // 注意线程启动得是start ， start之后，线程就能和别的线程竞争cpu时间片了，哪个线程获取cpu时间片， 就可以执行了
        // 但是注意 不是你获取了cpu时间片，就会一次把你的代码执行完毕，而是执行一会，cpu还会切换到别的线程执行，一会再切回来 ，cpu时间片切换是有算法的 最简单是轮询
        singerThread.start(); // 启动听歌线程  到这里 mainThread和singerThread 就会竞争cpu时间片了


        for (int i = 0 ; i< 100  ; i++)
        {
            System.out.println("--------------------------------------------我在看小说...." + i);
        }




//        for (int i = 0 ; i< 100  ; i++)
//        {
//            System.out.println("我在听歌......." + i);
//        }


    }


}


/**
 *  多线程实现方式有多种 1 实现Runable接口 2 继承Thread类 3 实现Callable接口
 */
class SingerThread extends Thread{

    // 实现run方法 线程切换过来时候就是执行run方法
    @Override
    public void run() {

        for (int i = 0 ; i< 100  ; i++)
        {
            System.out.println("我在听歌......." + i);
        }

    }
}