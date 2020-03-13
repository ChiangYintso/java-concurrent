package pers.jiangyinzuo.study.concurrent.s7.c15;

/**
 * @author Jiang Yinzuo
 */
public class CurrentThread {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        r.run();
        new Thread(r).start();
        new Thread(r).start();
    }
}
