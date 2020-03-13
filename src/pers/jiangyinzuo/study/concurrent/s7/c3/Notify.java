package pers.jiangyinzuo.study.concurrent.s7.c3;

/**
 * 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们。
 * start先执行不代表线程先启动
 *
 * @author Jiang Yinzuo
 */
public class Notify implements Runnable {

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " got resourceA");
            try {
                System.out.println(Thread.currentThread().getName() + " wait to start");
                resourceA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " done");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Notify();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        Thread.sleep(200);

        new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();
//                resourceA.notify();
                System.out.println("notified");
            }
        }).start();
    }
}
