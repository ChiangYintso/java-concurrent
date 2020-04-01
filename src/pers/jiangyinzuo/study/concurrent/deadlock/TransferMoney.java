package pers.jiangyinzuo.study.concurrent.deadlock;

/**
 * 转账时遇到了死锁
 *
 * @author Jiang Yinzuo
 */
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);

    static class Account {
        int balance = 0;

        Account(int balance) {
            this.balance = balance;
        }
    }

    @Override
    public void run() {
        if (flag == 1) {
            transfer(a, b, 200);
        }
        if (flag == 0) {
            transfer(b, a, 200);
        }
    }

    private void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                    return;
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a.balance + " " + b.balance);
    }
}
