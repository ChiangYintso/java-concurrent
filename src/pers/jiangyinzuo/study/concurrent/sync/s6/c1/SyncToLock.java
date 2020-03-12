package pers.jiangyinzuo.study.concurrent.sync.s6.c1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * method1和method2等价
 *
 * @author Jiang Yinzuo
 */
public class SyncToLock {
    Lock lock = new ReentrantLock();

    public synchronized void method1() {
        System.out.println("我是synchronized形式的锁");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("我是ReentrantLock");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SyncToLock s = new SyncToLock();
        s.method1();
        s.method2();
    }
}
