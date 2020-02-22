package pers.jiangyinzuo.study.concurrent.c4.s1;

/**
 * @author Jiang Yinzuo
 */
public class DoNotStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
