package pers.jiangyinzuo.study.concurrent.c5.s6;

/**
 * @author Jiang Yinzuo
 */
public class RunThrowException {
    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
