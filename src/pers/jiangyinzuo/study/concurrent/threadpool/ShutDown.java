package pers.jiangyinzuo.study.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 关闭线程池
 * @author Jiang Yinzuo
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable r = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };
        for (int i = 0; i < 1000; i++) {
            executorService.execute(r);
        }
        Thread.sleep(1500);
        System.out.println(executorService.isShutdown());
        System.out.println("开始关闭");
        executorService.shutdown();
        executorService.execute(r);
        System.out.println(executorService.isShutdown());
    }
}
