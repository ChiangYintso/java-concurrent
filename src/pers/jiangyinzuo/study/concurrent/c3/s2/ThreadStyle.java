package pers.jiangyinzuo.study.concurrent.c3.s2;

/**
 * 用Thread方式实现线程
 *
 * @author Jiang Yinzuo
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("run");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
