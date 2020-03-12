package pers.jiangyinzuo.study.concurrent.sync.s3.c7;

/**
 * 类锁
 * @author Jiang Yinzuo
 */
public class SyncClass implements Runnable {

    static SyncClass instance1 = new SyncClass();
    static SyncClass instance2 = new SyncClass();

    @Override
    public void run() {
        method();
    }

    private void method() {
        synchronized (SyncClass.class) {
            System.out.println("我是类锁 " + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();
    }
}
