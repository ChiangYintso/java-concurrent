package pers.jiangyinzuo.study.concurrent.s7.c4;

/**
 * wait只释放当前那把锁
 *
 * @author Jiang Yinzuo
 */
public class WaitRelease {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        new Thread(() ->{
            synchronized (resourceA) {
                System.out.println("A get resourceA");
                synchronized (resourceB) {
                    System.out.println("A get resourceB");
                    try {
                        resourceA.wait();
                        System.out.println("A release resourceA");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() ->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("B got resourceA");
                synchronized (resourceB) {
                    System.out.println("B got resourceB");
                }
            }
        }).start();
    }
}
