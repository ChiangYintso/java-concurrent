package pers.jiangyinzuo.study.concurrent.jmm;

/**
 * 1. 演示可见性带来的问题
 * 2. volatile可见性
 *
 * @author Jiang Yinzuo
 */
public class FieldVisibility {

    int a = 1;
    volatile int b = 2;

    public static void main(String[] args) {
        while (true) {
            FieldVisibility fieldVisibility = new FieldVisibility();
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
                if (fieldVisibility.a == 1 && fieldVisibility.b == 3) {

                    /**
                     * 情况1：a = 3, b = 3
                     * 情况2：a = 1, b = 2
                     * 情况3：a = 3, b = 2
                     * 情况4（概率低）：a = 1, b = 3, 因为b没加volatile
                     */
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
