package pers.jiangyinzuo.study.concurrent;

/**
 * 创建100个线程，在任务管理器的CPU中监视
 * @author Jiang Yinzuo
 */
public class CreateThread {
    public static void main(String[] args) {
        for (int i= 0; i < 1000; ++i) {
            new Thread(() -> {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
