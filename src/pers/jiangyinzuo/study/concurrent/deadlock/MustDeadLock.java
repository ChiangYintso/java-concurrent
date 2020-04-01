package pers.jiangyinzuo.study.concurrent.deadlock;

/**
 * 必定发生死锁
 * @author Jiang Yinzuo
 */
public class MustDeadLock implements Runnable {
    static Object o1 = new Object();
    static Object o2 = new Object();
    int flag = 1;

    @Override
    public void run() {
        System.out.println(flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {

                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {

                }
            }
        }
    }

    public static void main(String[] args) {
        MustDeadLock m1 = new MustDeadLock();
        MustDeadLock m2 = new MustDeadLock();

        m1.flag = 1;
        m2.flag = 0;

        new Thread(m1).start();
        new Thread(m2).start();
    }
}
