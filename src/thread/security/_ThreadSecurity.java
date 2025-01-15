package thread.security;

/**
 * 线程安全：多线程环境下，多个线程可能会访问相同的资源，因此需要进行同步以避免数据不一致的问题
 * 同步最常见的方案就是加锁:每次只允许一个线程加锁，加锁后才能进入访问，访问完毕后自动释放锁，然后其他线程才能再加锁进来
 * Java提供了三种加锁方案：
 * 1.同步代码块：synchronized关键字用于锁定对象或代码块，确保同一时刻只有一个线程可以访问
 * 2.同步方法：锁住整个方法,一个线程调用这个方法时另一个线程调用时会阻塞，等上一个线程调用结束后才能继续执行
 * 3.Lock锁：
 */
public class _ThreadSecurity
{
    // 1.同步代码块
    // 锁对象：必须是一个唯一的对象（同一个地址）
//    synchronized(锁对象){
//        //...访问共享数据的代码...
//    }

    // 2.同步方法
//    public synchronized void drawMoney(double money) {
//        // 先搞清楚是谁来取钱？
//        String name = Thread.currentThread().getName();
//        // 1、判断余额是否足够
//        if(this.money >= money){
//            System.out.println(name + "来取钱" + money + "成功！");
//            this.money -= money;
//            System.out.println(name + "来取钱后，余额剩余：" + this.money);
//        }else {
//            System.out.println(name + "来取钱：余额不足~");
//        }
//    }

    // 3.Lock锁
//    // 首先在成员变量位置，需要创建一个Lock接口的实现类对象（这个对象就是锁对象）
//    private final Lock lk = new ReentrantLock();
//    // 在需要上锁的地方加入下面的代码
//	  lk.lock(); // 加锁
//    //...中间是被锁住的代码...
//	  lk.unlock(); // 解锁

}
