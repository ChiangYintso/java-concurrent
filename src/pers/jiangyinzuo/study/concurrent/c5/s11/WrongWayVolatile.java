package pers.jiangyinzuo.study.concurrent.c5.s11;

/**
 * @author jiang
 */
public class WrongWayVolatile implements Runnable {
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                ++num;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile w = new WrongWayVolatile();
        Thread thread = new Thread(w);
        thread.start();
        Thread.sleep(2000);
        w.canceled = true;
    }
}
