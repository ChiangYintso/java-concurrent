package pers.jiangyinzuo.study.concurrent.s7.c11;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 每隔一秒钟输出当前时间
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 *
 * @author Jiang Yinzuo
 */
public class SleepInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                System.out.println(new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("我被中断了");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
