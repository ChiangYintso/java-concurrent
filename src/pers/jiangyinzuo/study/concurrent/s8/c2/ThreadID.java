package pers.jiangyinzuo.study.concurrent.s8.c2;

public class ThreadID {
    public static void main(String[] args) {
        Thread thread = new Thread("hello");
        System.out.println(Thread.currentThread().getId());
        System.out.println(thread.getId());
    }
}
