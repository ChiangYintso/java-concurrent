package pers.jiangyinzuo.study.concurrent.sync.s3.c6;

/**
 * 类锁的static形式
 *
 * @author Jiang Yinzuo
 */
public class SyncClassStatic implements Runnable {

    static SyncClassStatic  instance1 = new SyncClassStatic();
    static SyncClassStatic instance2 = new SyncClassStatic();

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        System.out.println("我是类锁 " + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();
    }
}
