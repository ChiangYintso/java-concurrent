package pers.jiangyinzuo.study.concurrent.sync.s4.c3;


/**
 * 同时访问静态synchronized和非静态synchronized方法
 *
 * @author Jiang Yinzuo
 */
public class SyncMethod implements Runnable {
    public static void main(String[] args) {
        SyncMethod s = new SyncMethod();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("加锁1" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }

    private static synchronized void method2() {
        System.out.println("静态加锁2" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }
}
