package pers.jiangyinzuo.study.concurrent.sync.s4.c2;

/**
 * 同时访问同步方法和非同步方法
 *
 * @author Jiang Yinzuo
 */
public class Sync implements Runnable {

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("加锁" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }

    private void method2() {
        System.out.println("没加锁" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Sync sync = new Sync();
        Thread t1 = new Thread(sync);
        Thread t2 = new Thread(sync);
        t1.start();
        t2.start();
    }
}
