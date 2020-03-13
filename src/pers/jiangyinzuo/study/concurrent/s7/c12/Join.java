package pers.jiangyinzuo.study.concurrent.s7.c12;

/**
 * 演示join()
 *
 * @author Jiang Yinzuo
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        };
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();
        System.out.println("等待子线程");

        thread1.join();
        thread2.join();
        System.out.println("子线程执行完毕");
    }
}
