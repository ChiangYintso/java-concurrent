package pers.jiangyinzuo.study.concurrent.s7.c13;

/**
 *
 * join()的等价代码
 *
 * @author Jiang Yinzuo
 */
public class JoinEqual {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 done");
        });
        thread.start();
        System.out.println("waiting");
//        thread.join();
        synchronized (thread) {
            thread.wait();
        }
        System.out.println("main done");
    }
}
