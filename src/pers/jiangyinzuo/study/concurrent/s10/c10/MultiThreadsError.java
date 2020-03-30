package pers.jiangyinzuo.study.concurrent.s10.c10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class MultiThreadsError {
    private Map<String, String> map = new HashMap<>();

    public MultiThreadsError() {
        new Thread(() -> {
            map.put("1", "A");
            map.put("2", "B");
        }).start();

    }

    public Map<String, String> getMap() {
        return map;
    }

    public static void main(String[] args) {
        MultiThreadsError multiThreadsError = new MultiThreadsError();
        Map<String, String> map = multiThreadsError.getMap();
        System.out.println(map.get("1"));
        map.remove("1", "A");
    }
}
