package pers.jiangyinzuo.study.concurrent.c5.s4;

/**
 * @author Jiang Yinzuo
 */
public class StopThreadWhenSleeping {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num < 30000000) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                ++num;
            }
            if (num < 30000000) {
                System.out.println("线程被中断");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
