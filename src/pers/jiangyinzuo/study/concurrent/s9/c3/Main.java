package pers.jiangyinzuo.study.concurrent.s9.c3;

public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(
                new MyExceptionHandler("捕获器1")
        );
        for (int i = 0; i < 5; ++i) {
            new Thread(() -> {
                throw new RuntimeException();
            }).start();
        }
    }
}
