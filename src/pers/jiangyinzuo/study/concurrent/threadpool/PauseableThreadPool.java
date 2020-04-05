package pers.jiangyinzuo.study.concurrent.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示每个任务执行前后都能放钩子函数
 *
 * @author Jiang Yinzuo
 */
public class PauseableThreadPool extends ThreadPoolExecutor {
    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    private boolean isPaused;
    private final Lock lock = new ReentrantLock();
    private Condition unpaused = lock.newCondition();

    public void pause() {
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PauseableThreadPool pauseableThreadPool = new PauseableThreadPool(10, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 10000; i++) {
            pauseableThreadPool.execute(() -> {
                System.out.println("我被执行");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pauseableThreadPool.pause();
        System.out.println("线程池被暂停了");
        Thread.sleep(10000);
        System.out.println("线程池即将启动");
        Thread.sleep(3000);
        pauseableThreadPool.resume();
    }
}
