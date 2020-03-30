package pers.jiangyinzuo.study.concurrent.s9.c1;

/**
 * 1. 不加try-catch抛出4个异常，都带线程名
 * 2. 加try-catch期望捕获到第一个线程的异常，线程234不应该运行，希望看到caught exception
 * 3. 执行时发现根本没有catch exception，线程234依然运行，且抛出异常
 *
 * @author Jiang Yinzuo
 */
public class CannotCatchDirectly {
    public static void main(String[] args) {
        Runnable r = () -> {
            throw new RuntimeException();
        };
        try {
            for (int i = 1; i <= 4; ++i) {
                new Thread(r, "thread" + i).start();
            }
        } catch (RuntimeException e) {
            // 并不会执行到
            System.out.println("catch exception");
        }

    }
}
