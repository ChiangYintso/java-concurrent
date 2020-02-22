package pers.jiangyinzuo.study.concurrent.c3.s2;

/**
 * 用Runnable方式创建线程
 *
 * @author Jiang Yinzuo
 */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("run");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}
