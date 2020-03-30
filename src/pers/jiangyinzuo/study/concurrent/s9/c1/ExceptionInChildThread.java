package pers.jiangyinzuo.study.concurrent.s9.c1;

/**
 * @author Jiang Yinzuo
 */
public class ExceptionInChildThread {
    public static void main(String[] args) {
        new Thread(() -> {
            throw new RuntimeException();
        }).start();
        for (int i = 0; i < 100; ++i) {
            System.out.println("hello");
        }
    }
}
