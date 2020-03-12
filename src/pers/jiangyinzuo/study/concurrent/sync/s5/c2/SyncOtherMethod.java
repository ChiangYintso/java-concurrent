package pers.jiangyinzuo.study.concurrent.sync.s5.c2;

/**
 * 可重入粒度测试：调用类内另外的方法
 * @author Jiang Yinzuo
 */
public class SyncOtherMethod {
    private synchronized void method1() {
        System.out.println("我是方法1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("我是方法2");
    }

    public static void main(String[] args) {
        SyncOtherMethod s = new SyncOtherMethod();
        s.method1();
    }
}
