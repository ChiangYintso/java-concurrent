package pers.jiangyinzuo.study.concurrent.c3.s3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建线程的方法
 *
 * @author Jiang Yinzuo
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; ++i) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }
}
