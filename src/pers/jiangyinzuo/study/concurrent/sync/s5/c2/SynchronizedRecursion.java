package pers.jiangyinzuo.study.concurrent.sync.s5.c2;

/**
 * 可重入粒度测试: 调用自身
 *
 * @author Jiang Yinzuo
 */
public class SynchronizedRecursion {

    private int a = 1;

    private synchronized void method1() {
        System.out.println("method1, a=" + a);
        if (a == 1) {
            a--;
            method1();
        }
    }

    public static void main(String[] args) {
        SynchronizedRecursion s = new SynchronizedRecursion();
        s.method1();
    }
}
