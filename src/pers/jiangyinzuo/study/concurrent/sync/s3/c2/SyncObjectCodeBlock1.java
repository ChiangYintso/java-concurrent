package pers.jiangyinzuo.study.concurrent.sync.s3.c2;

/**
 * 对象锁示例：代码块形式
 *
 * @author Jiang Yinzuo
 */
public class SyncObjectCodeBlock1 implements Runnable {

    static SyncObjectCodeBlock1 instance = new SyncObjectCodeBlock1();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("对象锁" + Thread.currentThread().getName());
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
