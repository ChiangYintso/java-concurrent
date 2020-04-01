package pers.jiangyinzuo.study.concurrent.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用volatile的场景
 */
public class NoVolatile implements Runnable{
    int a = 0;
    AtomicInteger realA = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            a++;
            realA.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoVolatile noVolatile = new NoVolatile();
        Thread t1 = new Thread(noVolatile);
        Thread t2 = new Thread(noVolatile);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(noVolatile.a);
        System.out.println(noVolatile.realA.get());
    }
}
