package pers.jiangyinzuo.study.concurrent.c5.s14;

/**
 * Thread.interrupted()方法目标是当前线程
 */
public class Interrupted {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.interrupt();
        System.out.println(t.isInterrupted());
        System.out.println(t.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println(t.isInterrupted());
    }
}
