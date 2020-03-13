package pers.jiangyinzuo.study.concurrent.s7.c10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep方法不释放Lock锁
 *
 * @author Jiang Yinzuo
 */
public class SleepDoNotReleaseLock implements Runnable {

    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDoNotReleaseLock s = new SleepDoNotReleaseLock();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        t1.start();
        t2.start();
    }
}
