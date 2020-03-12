package pers.jiangyinzuo.study.concurrent.sync.s3.c2;

/**
 * 对象锁示例：代码块形式
 *
 * @author Jiang Yinzuo
 */
public class SyncObjectCodeBlock2 implements Runnable {

    static SyncObjectCodeBlock2 instance = new SyncObjectCodeBlock2();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("lock1" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }

        synchronized (lock2) {
            System.out.println("lock2" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
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
