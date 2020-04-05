package pers.jiangyinzuo.study.concurrent.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jiang Yinzuo
 */
public class ShutdownNow {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("停止");
                }
            });
        }
        List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables.size());
    }
}
