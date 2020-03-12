package pers.jiangyinzuo.study.concurrent.sync.s4.c2;

/**
 * 同时访问一个对象的不同的同步方法
 *
 * @author Jiang Yinzuo
 */
public class SyncDifferentMethod implements Runnable{
    public static void main(String[] args) {
        SyncDifferentMethod s = new SyncDifferentMethod();
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

    private synchronized void method2() {
        System.out.println("加锁2" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }
}
