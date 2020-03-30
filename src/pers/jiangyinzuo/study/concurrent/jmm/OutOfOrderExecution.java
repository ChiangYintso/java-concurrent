package pers.jiangyinzuo.study.concurrent.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序的现象
 * 测试小概率事件
 *
 * @author Jiang Yinzuo
 */
public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            x = y = a = b = 0;
            i++;
            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread two = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();

            System.out.println("第" + i + "次");
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            if (x == y && y == 0) {
                break;
            }
        }

    }
}
