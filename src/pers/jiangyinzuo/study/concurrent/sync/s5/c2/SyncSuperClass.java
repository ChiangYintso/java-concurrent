package pers.jiangyinzuo.study.concurrent.sync.s5.c2;

/**
 * 可重入粒度测试：调用父类方法
 *
 * @author Jiang Yinzuo
 */
public class SyncSuperClass extends Super {
    @Override
    public synchronized void method() {
        System.out.println("子类方法");
        super.method();
    }
    public static void main(String[] args) {
        new SyncSuperClass().method();
    }
}

class Super {
    public synchronized void method() {
        System.out.println("父类方法");
    }
}
