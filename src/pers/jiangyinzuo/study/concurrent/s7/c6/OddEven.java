package pers.jiangyinzuo.study.concurrent.s7.c6;

/**
 * @author Jiang Yinzuo
 */
public class OddEven implements Runnable {

    private static int num = 0;

    public static void main(String[] args) {
        OddEven oddEven = new OddEven();
        Thread thread1 = new Thread(oddEven);
        Thread thread2 = new Thread(oddEven);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        while (num <= 100) {
            synchronized (OddEven.class) {
                System.out.println(Thread.currentThread().getName() + " " + num);
                ++num;
                OddEven.class.notify();
                if (num <= 100) {
                    try {
                        OddEven.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
