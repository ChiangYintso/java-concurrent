package pers.jiangyinzuo.study.concurrent.deadlock;

import java.util.Random;

/**
 * 多人随机转账
 *
 * @author Jiang Yinzuo
 */
public class MultiTransferMoney {
    private static final int ACCOUNTS = 20;
    public static void main(String[] args) {
        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[ACCOUNTS];
        for (int i = 0; i < accounts.length; ++i) {
            accounts[i] = new TransferMoney.Account(1000);
        }
        for (int j = 0; j < 20; j++) {
            new Thread(() -> {
                for (int i= 0; i < 1000000; ++i) {
                    int from = random.nextInt(ACCOUNTS);
                    int to = random.nextInt(ACCOUNTS);
                    int amount = random.nextInt(ACCOUNTS);
                    TransferMoney.transfer(accounts[from], accounts[to], amount);
                }
            }).start();
        }

    }
}
