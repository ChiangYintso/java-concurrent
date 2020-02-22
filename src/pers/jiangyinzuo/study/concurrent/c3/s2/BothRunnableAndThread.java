package pers.jiangyinzuo.study.concurrent.c3.s2;

/**
 * 同时使用Runnable和Thread
 *
 * @author Jiang Yinzuo
 */
public class BothRunnableAndThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Thread");
            }
        }.start();
    }
}
