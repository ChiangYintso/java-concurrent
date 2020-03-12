package pers.jiangyinzuo.study.concurrent.s6.c3;

/**
 * 展示线程的NEW、RUNNABLE、TERMINATED状态
 * 即使是正在运行也是RUNNABLE状态，而不是running
 * @author jiang
 */
public class NewRunnableTerminated implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new NewRunnableTerminated());
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            if (i % 2 == 1) {
                System.out.println(i);
            }
        }
    }
}
