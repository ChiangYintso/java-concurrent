package pers.jiangyinzuo.study.concurrent.c4.s1;

/**
 * 对比start()和run()两种启动线程的方式
 *
 * @author Jiang Yinzuo
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();
        new Thread(runnable).start();
    }
}
