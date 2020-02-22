package pers.jiangyinzuo.study.concurrent.c5.s4;

/**
 * @author Jiang Yinzuo
 */
public class StopThreadWhenSleepingInEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                // 不需要每次迭代都检查isInterrupted()
                while (num < 300000) {
                    if (num % 100 == 0) {
                        System.out.println(num);
                    }
                    ++num;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
    }
}
