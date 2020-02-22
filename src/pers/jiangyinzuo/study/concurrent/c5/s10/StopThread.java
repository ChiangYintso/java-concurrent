package pers.jiangyinzuo.study.concurrent.c5.s10;

/**
 * 错误的停止线程方法：
 * 用stop来停止线程会导致线程运行到一半突然停止,
 * 没法完成一个基本单位的操作, 造成脏数据
 *
 * @author jiang
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            System.out.println(i + "号领取武器");
            for (int j = 0; j < 10; ++j) {
                System.out.println(j);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StopThread());
        t.start();
        Thread.sleep(300);
        t.stop();
    }
}
