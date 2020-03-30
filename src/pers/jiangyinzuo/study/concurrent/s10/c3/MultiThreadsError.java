package pers.jiangyinzuo.study.concurrent.s10.c3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程运行结果出错
 * 1. 计数结果不准确
 *
 * @author Jiang Yinzuo
 */
public class MultiThreadsError implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError r = new MultiThreadsError();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.idx);
        System.out.println(wrong);
        System.out.println(tot);
    }

    int idx = 0;
    static AtomicInteger tot = new AtomicInteger(0);
    static AtomicInteger wrong = new AtomicInteger(0);
    static boolean[] added = new boolean[30000];
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            idx++;
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (added[idx] && added[idx - 1]) {
                    System.out.println("发生错误" + idx);
                    wrong.incrementAndGet();
                }
                added[idx] = true;
                tot.incrementAndGet();
            }
        }
    }
}
