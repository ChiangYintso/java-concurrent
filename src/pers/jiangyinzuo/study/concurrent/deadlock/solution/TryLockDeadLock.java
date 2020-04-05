package pers.jiangyinzuo.study.concurrent.deadlock.solution;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用tryLock来避免死锁
 *
 * @author Jiang Yinzuo
 */
public class TryLockDeadLock {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10000; ++i) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        System.out.println("线程1拿到锁1");
                        Thread.sleep(500);
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            System.out.println("线程1成功获取两把锁");
                            lock2.unlock();
                            lock1.unlock();
                        } else {
                            System.out.println("线程1获取锁2失败，已重试");
                            lock1.unlock();
                            Thread.sleep((long) (Math.random() * 1000));
                        }
                    } else {
                        System.out.println("线程1获取锁1失败，已重试");
                        Thread.sleep((long) (Math.random() * 1000));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000; ++i) {
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        System.out.println("线程2拿到锁2");
                        Thread.sleep(500);
                        if (lock1.tryLock(3000, TimeUnit.MILLISECONDS)) {
                            System.out.println("线程2成功获取两把锁");
                            lock1.unlock();
                            lock2.unlock();
                        } else {
                            System.out.println("线程2获取锁1失败，已重试");
                            lock2.unlock();
                            Thread.sleep((long) (Math.random() * 1000));
                        }
                    } else {
                        System.out.println("线程2获取锁2失败，已重试");
                        Thread.sleep((long) (Math.random() * 1000));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
