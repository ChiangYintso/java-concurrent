package pers.jiangyinzuo.study.concurrent.c3.s3;

/**
 * @author Jiang Yinzuo
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
