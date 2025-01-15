package thread.threadpool;

import java.util.concurrent.*;

/**
 * 线程池：线程复用技术，可以避免频繁创建和销毁线程带来的性能开销，提高线程的利用率
 * 1.新任务提交时,若核心线程都在忙且任务队列已满,此时可以创建临时线程。若核心线程和临时线程都在忙且任务队列也满了，则会开始拒绝新任务
 * 2.线程池执行的任务可以有两种，一种是 Runnable任务，一种是 callable任务：
 * Runnable：适用于简单的、不需要返回结果的任务。
 * Callable：适用于需要返回结果或可能抛出异常的任务，通常与 FutureTask结合使用来获取结果。
 * Runnable接口是最常用的定义线程任务的方式。它只有一个方法run()，该方法不返回任何结果且不能抛出受检异常
 * Callable接口与 Runnable类似，但它允许任务返回结果，并且可以抛出异常。它有一个泛型参数，用于指定返回结果的类型。
 * 3.最大线程数maximumPoolSize：计算密集型任务应设置为 CPU核数 + 1，IO密集型任务应设置为 CPU核数*2
 */
public class _ThreadPool
{
    public static void main(String[] args)
    {
        // ExecutorService：用于管理线程池和任务执行
        // 使用ThreadPoolExecutor类就可以用来创建线程池对象
//        ThreadPoolExecutor(
//                int corePoolSize, 核心线程数（正式工）
//                int maximumPoolSize, 最大线程数，临时线程数（临时工）=最大线程数-核心线程数
//                long keepAliveTime, 临时线程存活的时间（临时员工空闲多久被开除）
//                TimeUnit unit, 临时线程存活的时间单位（时、分、秒...）
//                BlockingQueue<Runnable> workQueue, 任务阻塞队列（客人排队的地方）
//                ThreadFactory threadFactory, 线程池的线程工厂（负责招聘员工的HR）
//                RejectedExecutionHandler handler, 拒绝策略（忙不过来怎么办）
//        )

        ExecutorService pool = new ThreadPoolExecutor(
                3,    // 核心线程数有3个
                5,  // 最大线程数有5个。   临时线程数=最大线程数-核心线程数=5-3=2
                8,    // 临时线程存活的时间8秒。 意思是临时线程8秒没有任务执行，就会被销毁掉。
                TimeUnit.SECONDS,// 临时线程存活的时间单位（秒）
                new ArrayBlockingQueue<>(4), // 任务阻塞队列，没有来得及执行的任务在任务队列中等待
                Executors.defaultThreadFactory(), // 负责创建临时线程的工厂对象
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );
    }
}
