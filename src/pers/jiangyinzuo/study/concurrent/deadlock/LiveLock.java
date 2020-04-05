package pers.jiangyinzuo.study.concurrent.deadlock;

import java.util.Random;

/**
 * 活锁演示
 *
 * @author Jiang Yinzuo
 */
public class LiveLock {
    static class Spoon {
        private Diner owner;

        Spoon(Diner owner) {
            this.owner = owner;
        }

        synchronized void use() {
            System.out.println(owner.name + "吃饭");
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) throws InterruptedException {
            while (isHungry) {
                if (spoon.owner != this) {
                    Thread.sleep(1);
                    continue;
                }

                // 引入随机因素解决活锁问题
                if (spouse.isHungry && new Random().nextInt(10) < 8) {
                    System.out.println(name + ":" + spouse.name + " 先吃");
                    spoon.owner = spouse;
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + ": 吃饱了");
                spoon.owner = spouse;
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("Bob");
        Diner wife = new Diner("Alice");
        Spoon spoon = new Spoon(husband);
        new Thread(() -> {
            try {
                husband.eatWith(spoon, wife);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                wife.eatWith(spoon, husband);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
