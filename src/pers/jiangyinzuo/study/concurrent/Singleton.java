package pers.jiangyinzuo.study.concurrent;

/**
 * 单例模式
 * @author Jiang Yinzuo
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {}

    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
