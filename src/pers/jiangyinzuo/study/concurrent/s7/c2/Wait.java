package pers.jiangyinzuo.study.concurrent.s7.c2;

/**
 * wait和notify的基本用法
 * <p>
 * 1. 研究代码执行顺序
 * 2. 证明wait释放锁
 *
 * @author Jiang Yinzuo
 */
public class Wait {

    public static Object object = new Object();

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName()+ "调用了notify");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();

        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
