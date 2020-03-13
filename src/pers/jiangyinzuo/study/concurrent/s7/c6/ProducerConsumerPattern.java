package pers.jiangyinzuo.study.concurrent.s7.c6;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Jiang Yinzuo
 */
public class ProducerConsumerPattern {
    static class Consumer implements Runnable {

        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; ++i) {
                try {
                    Date res = storage.take();
                    System.out.println(res + "被消费了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable {

        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; ++i) {
                try {
                    storage.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class EventStorage {
        private int maxSize;
        private List<Date> storage;

        public EventStorage() {
            maxSize = 10;
            storage = new ArrayList<>();
        }

        public synchronized void put() throws InterruptedException {
            while (storage.size() >= maxSize) {
                wait();
            }
            storage.add(new Date());
            System.out.println("仓库里有了" + storage.size() + "个产品");
            notify();
        }

        public synchronized Date take() throws InterruptedException {
            while (storage.isEmpty()) {
                wait();
            }

            Date result =  storage.remove(0);
            System.out.println("还剩" + storage.size() + "个");
            notify();
            return result;
        }
    }

    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}
