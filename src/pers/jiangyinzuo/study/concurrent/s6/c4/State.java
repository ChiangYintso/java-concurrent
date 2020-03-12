package pers.jiangyinzuo.study.concurrent.s6.c4;

/**
 *
 * 展示Blocked，Waiting，TimedWaiting
 *
 * @author jiang
 */
public class State implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        State s = new State();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();
        Thread.sleep(5);

        // 输出TIMED_WAITING: 因为正在执行Thread.sleep(1000);
        System.out.println(t1.getState());

        // 输出BLOCKED: 因为拿不到锁
        System.out.println(t2.getState());

        Thread.sleep(1300);

        // 输出WAITING: 因为执行了wait()
        System.out.println(t1.getState());
    }

    @Override
    public void run() {
        try {
            sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sync() throws InterruptedException {
        Thread.sleep(1000);
        wait();
    }
}
