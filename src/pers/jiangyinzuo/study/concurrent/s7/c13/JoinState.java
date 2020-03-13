package pers.jiangyinzuo.study.concurrent.s7.c13;

/**
 * @author Jiang Yinzuo
 */
public class JoinState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.println(mainThread.getState());
    }
}
