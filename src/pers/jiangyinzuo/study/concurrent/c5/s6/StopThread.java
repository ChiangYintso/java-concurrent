package pers.jiangyinzuo.study.concurrent.c5.s6;

/**
 * catch InterruptedException后优先选择在方法签名中抛出异常
 *
 * @author Jiang Yinzuo
 */
public class StopThread implements Runnable {

    private void throwInMethod() throws InterruptedException {
        System.out.println("go");
        Thread.sleep(3000);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("保存日志");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("线程中断成功");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("线程中断，程序继续运行");
    }
}
