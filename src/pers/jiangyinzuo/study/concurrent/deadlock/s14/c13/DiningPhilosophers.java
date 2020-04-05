package pers.jiangyinzuo.study.concurrent.deadlock.s14.c13;

/**
 * 哲学家就餐问题
 * @author Jiang Yinzuo
 */
public class DiningPhilosophers {

    static class Philosopher implements Runnable {

        private final Object leftFork;
        private final Object rightFork;

        public Philosopher(Object leftFork, Object rightFork) {
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    thinking();
                    eating();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void thinking() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " is thinking...");
            Thread.sleep((long) (Math.random() * 10));
        }

        private void eating() throws InterruptedException {
            synchronized (leftFork) {
                System.out.println(Thread.currentThread().getName() + " pick up left fork");
                synchronized (rightFork) {
                    System.out.println(Thread.currentThread().getName() + " pick up right fork");
                    System.out.println(Thread.currentThread().getName() + " is eating");
                    Thread.sleep((long) (Math.random() * 10));
                    System.out.println(Thread.currentThread().getName() + " put down right fork");
                }
            }
            System.out.println(Thread.currentThread().getName() + " put down left fork");
        }
    }

    public static void main(String[] args) {
        final Object[] forks = new Object[5];
        for (int i = 0; i < 5; ++i) {
            forks[i] = new Object();
        }
        Philosopher[] philosophers = new Philosopher[5];
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; ++i) {
            // 改变一个哲学家拿餐叉的顺序（避免策略）
            if (i == 4) {
                philosophers[i] = new Philosopher(forks[(i+1) % 5], forks[i]);
            } else {
                philosophers[i] = new Philosopher(forks[i], forks[(i+1) % 5]);

            }
            threads[i] = new Thread(philosophers[i]);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
