package pers.jiangyinzuo.study.concurrent.s10.c8c11;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象逸出：方法返回一个private对象（本意不让外界修改）
 * @author Jiang Yinzuo
 */
public class MultiThreadsError {
    private Map<String, String> map = new HashMap<>();

    public MultiThreadsError() {
        map.put("1", "A");
        map.put("2", "B");
    }

    /**
     * 直接返回导致map私有对象被修改
     * @return map
     */
    public Map<String, String> getMap() {
        return map;
    }

    /**
     * 改进方法
     * @return map的副本
     */
    public Map<String, String> getMapCopy() {
        return new HashMap<>(map);
    }

    public static void main(String[] args) {
        MultiThreadsError multiThreadsError = new MultiThreadsError();
        Map<String, String> map = multiThreadsError.getMap();
        map.remove("1", "A");
    }
}
