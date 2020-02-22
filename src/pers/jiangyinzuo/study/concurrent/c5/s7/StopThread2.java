package pers.jiangyinzuo.study.concurrent.c5.s7;

/**
 * 在catch语句中调用Thread.currentThread().interrupt()
 * 来恢复设置中断状态, 以便于在后续的执行中, 依然能够检查到刚才发生的中断
 *
 * @author Jiang Yinzuo
 */
public class StopThread2 implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            reInterrupt();
        }
        System.out.println("程序结束");
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StopThread2());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
