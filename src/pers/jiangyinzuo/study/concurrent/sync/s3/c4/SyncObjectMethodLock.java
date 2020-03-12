package pers.jiangyinzuo.study.concurrent.sync.s3.c4;


/**
 * 对象锁：方法锁
 * @author Jiang Yinzuo
 */
public class SyncObjectMethodLock implements Runnable {
    static SyncObjectMethodLock instance = new SyncObjectMethodLock();

    @Override
    public synchronized void run() {
        System.out.println("对象锁 方法修饰" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        // t1.join();
        // t2.join();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("Done!");
    }
}
