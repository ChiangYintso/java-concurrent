package pers.jiangyinzuo.study.concurrent.sync.s4.c3;

/**
 * 同步代码块中调用非同步方法，非同步方法没有加锁，线程不安全
 * @author Jiang Yinzuo
 */
public class SyncMethod2 implements Runnable {
    public static void main(String[] args) {
        SyncMethod2 s = new SyncMethod2();
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
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("加锁1" + Thread.currentThread().getName());
        method2();
        System.out.println("done" + Thread.currentThread().getName());
    }

    private static void method2() {
        System.out.println("方法2" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done2" + Thread.currentThread().getName());
    }
}
