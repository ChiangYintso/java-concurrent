package pers.jiangyinzuo.study.concurrent.c3.s3;

import java.util.Timer;

/**
 * 定时器创建线程
 *
 * @author Jiang Yinzuo
 */
public class TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1, 1000);
    }
}
