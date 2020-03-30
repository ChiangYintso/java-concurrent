package pers.jiangyinzuo.study.concurrent.jmm;

/**
 * 演示可见性带来的问题
 * @author Jiang Yinzuo
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;

    public static void main(String[] args) {
        while (true) {
            FieldVisibility fieldVisibility = new FieldVisibility();
            fieldVisibility.change();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fieldVisibility.change();
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (fieldVisibility.a != 3 && fieldVisibility.b != 3) {
                    fieldVisibility.print();
                }
            }).start();

        }

    }

    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.println("b = " + b + "; a = " + a);
    }
}
