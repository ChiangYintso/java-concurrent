package pers.jiangyinzuo.study.concurrent.threadlocal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用两个线程打印生日信息
 *
 * @author Jiang Yinzuo
 */
public class SimpleDateFormatTest {
    public static void toDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
    }

    public static void main(String[] args) throws ScriptException {
        new Thread(() -> toDate(System.currentTimeMillis())).start();
        new Thread(() -> toDate(1000L)).start();
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javascript = scriptEngineManager.getEngineByName("javascript");
        System.out.println(javascript.eval("""
                function foo(a, b) {
                    return a + b;
                }
                foo("hello", " world");
                """));
    }
}
