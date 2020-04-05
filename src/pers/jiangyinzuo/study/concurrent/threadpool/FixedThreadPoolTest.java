package pers.jiangyinzuo.study.concurrent.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示FixedThreadPool
 * @author Jiang Yinzuo
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Runnable r = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };
        for (int i = 0; i < 1000; ++i) {
            executorService.execute(r);
        }
        executorService.shutdown();
    }
}
