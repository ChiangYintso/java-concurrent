package pers.jiangyinzuo.study.concurrent.s7.c10;

/**
 * @author Jiang Yinzuo
 */
public class SleepDonotReleaseMonitor implements Runnable {
    @Override
    public synchronized void run() {
        System.out.println("start" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        SleepDonotReleaseMonitor s = new SleepDonotReleaseMonitor();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        t1.start();
        t2.start();
    }
}
