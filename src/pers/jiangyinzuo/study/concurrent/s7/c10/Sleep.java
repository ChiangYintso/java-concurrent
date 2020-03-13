package pers.jiangyinzuo.study.concurrent.s7.c10;

public class Sleep {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done");
        });
        thread.start();
        Thread.sleep(300);
        System.out.println(thread.getState());
    }
}
