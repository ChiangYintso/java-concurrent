package pers.jiangyinzuo.study.concurrent.s6.c3;

/**
 * 不能两次调用start方法, 否则会报错
 * @author jiang
 */
public class CannotStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
