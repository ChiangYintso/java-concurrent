package pers.jiangyinzuo.study.concurrent.sync.s2.c4;

/**
 *
 *
 * @author Jiang Yinzuo
 */
public class DisappearRequest implements Runnable {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DisappearRequest());
        Thread t2 = new Thread(new DisappearRequest());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; ++j) {
            ++i;
        }
    }
}
