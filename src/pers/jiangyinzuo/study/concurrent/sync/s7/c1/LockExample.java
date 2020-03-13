package pers.jiangyinzuo.study.concurrent.sync.s7.c1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的用法
 *
 * @author Jiang Yinzuo
 */
public class LockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        lock.tryLock();
    }
}
