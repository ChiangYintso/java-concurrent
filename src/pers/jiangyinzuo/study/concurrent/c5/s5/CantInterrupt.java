package pers.jiangyinzuo.study.concurrent.c5.s5;

/**
 * while里面放try-catch，会导致中断失效
 *
 * @author Jiang Yinzuo
 */
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num <= 10000) {
                if (num % 102 == 0) {
                    System.out.println(num);
                }
                ++num;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
        Thread.sleep(1500);
        thread.interrupt();
    }
}
