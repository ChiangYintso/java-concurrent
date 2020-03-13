package pers.jiangyinzuo.study.concurrent.s7.c13;

/**
 * @author Jiang Yinzuo
 */
public class JoinInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Thread1 finished");
            } catch (InterruptedException e) {
                System.out.println("Thread1被中断");
            }
        });
        t.start();
        System.out.println("等待子线程");
        Thread.sleep(1000);

        Thread.currentThread().interrupt();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
            t.interrupt();
        }
        System.out.println("main finished");
    }
}
