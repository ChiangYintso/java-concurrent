package pers.jiangyinzuo.study.concurrent.sync.s4.c3;

/**
 * 方法抛异常后会释放锁。展示不抛出异常前和抛出异常后的对比：
 * 一旦抛出异常，第二个线程会立刻进入同步方法，意味着锁已经释放
 *
 * @author Jiang Yinzuo
 */
public class ThrowException implements Runnable {
    public static void main(String[] args) {
        ThrowException s = new ThrowException();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("加锁1" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
//        System.out.println("done" + Thread.currentThread().getName());
    }

    private synchronized void method2() {
        System.out.println("加锁2" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done" + Thread.currentThread().getName());
    }
}
