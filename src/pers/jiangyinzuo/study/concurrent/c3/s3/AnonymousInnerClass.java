package pers.jiangyinzuo.study.concurrent.c3.s3;

/**
 * @author Jiang Yinzuo
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
