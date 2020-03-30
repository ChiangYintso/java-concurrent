package pers.jiangyinzuo.study.concurrent.s9.c3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jiang Yinzuo
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;
    public MyExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常" + t.getName(), e);
        System.out.println(name + "捕获了异常" + e);
    }
}
