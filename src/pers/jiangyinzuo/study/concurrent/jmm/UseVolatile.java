package pers.jiangyinzuo.study.concurrent.jmm;

/**
 * 适用volatile的场景: 触发器
 * @author Jiang Yinzuo
 */
public class UseVolatile implements Runnable{
    int[] arr;
    volatile boolean initialized = false;
    public static void main(String[] args) throws InterruptedException {
        UseVolatile useVolatile = new UseVolatile();
        new Thread(useVolatile).start();
        while (!useVolatile.initialized) {
            Thread.sleep(10);
        }
        System.out.println(useVolatile.arr[999]);
    }

    @Override
    public void run() {
        arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        initialized = true;
    }
}
