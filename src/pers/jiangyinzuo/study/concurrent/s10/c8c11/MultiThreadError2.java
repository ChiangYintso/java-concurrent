package pers.jiangyinzuo.study.concurrent.s10.c8c11;

/**
 * 对象逸出：构造函数未完成就将对象提供给外界
 * @author Jiang Yinzuo
 */
public class MultiThreadError2 {
    static Point p;

    public static void main(String[] args) {
        new Thread(() -> {
            new Point(2, 3);
        }).start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (p != null) {
            System.out.println(p);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(p);
    }
}

class Point {
    private final int x, y;

    Point(int x, int y) {
        this.x = x;
        MultiThreadError2.p = this;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
