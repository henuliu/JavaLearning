package thread.threadpool;

import java.util.concurrent.*;

/**
 * 线程池执行 Runnable任务
 */
public class ThreadPoolRunnabkeTest
{
    public static void main(String[] args)
    {
        // 创建一个线程池对象
        ExecutorService pool = new ThreadPoolExecutor(
                3,    //核心线程数有3个
                Runtime.getRuntime().availableProcessors() + 1,  // 最大线程数(cpu核数+1)。临时线程数=最大线程数-核心线程数
                8,    // 临时线程存活的时间8秒。 意思是临时线程8秒没有任务执行，就会被销毁掉。
                TimeUnit.SECONDS,//时间单位（秒）
                new ArrayBlockingQueue<>(4), //任务队列，没有来得及执行的任务在，任务队列中等待
                Executors.defaultThreadFactory(), //用于创建线程的工厂对象
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );
        System.out.println(Runtime.getRuntime().availableProcessors());

        Runnable target = new MyRunnable();
        pool.execute(target); // 线程池会自动创建一个新线程，自动处理这个任务，自动执行的！
        pool.execute(target); // 线程池会自动创建一个新线程，自动处理这个任务，自动执行的！
        pool.execute(target); // 线程池会自动创建一个新线程，自动处理这个任务，自动执行的！

        // 下面4个任务在任务队列里排队,等待被核心线程执行
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);

        // 下面2个任务，由于核心线程在忙且任务队列已满，到了临时线程的创建时机了
        pool.execute(target);
        pool.execute(target);

        // 由于核心线程和临时线程在忙且任务队列已满，新任务被拒绝。由于使用了CallerRunsPolicy()策略，最后会被main线程执行
        pool.execute(target);

        // 等到线程池的任务全部执行完毕后，再关闭线程池
        pool.shutdown();

        // 立即关闭线程池，不管任务是否执行完毕，同时可以返回所有未执行完的线程
//        List<Runnable> runnables = pool.shutdownNow();
    }
}
