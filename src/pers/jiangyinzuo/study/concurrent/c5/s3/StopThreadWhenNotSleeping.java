package pers.jiangyinzuo.study.concurrent.c5.s3;

/**
 * run方法内没有sleep或wait方法时, 停止线程
 *
 * @author Jiang Yinzuo
 */
public class StopThreadWhenNotSleeping implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num);
            }
            ++num;
        }
        System.out.println("Done!");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThreadWhenNotSleeping());
        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
    }
}
