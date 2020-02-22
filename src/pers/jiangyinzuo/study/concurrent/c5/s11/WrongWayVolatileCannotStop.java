package pers.jiangyinzuo.study.concurrent.c5.s11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * volatile的局限
 * 陷入阻塞时, volatile是无法停止线程的
 * 此例中, 生产者的生产速度很快; 消费者生产速度慢
 * 阻塞队列满了后, 生产者会阻塞, 等待消费者进一步消费
 *
 * @author jiang
 */
public class WrongWayVolatileCannotStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer consumer = new Consumer(queue);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费结束");

        // 消费者不需要数据后, 生产者应当停下来
        producer.canceled = true;
        System.out.println("生产者应该停下来了");
    }
}

class Producer implements Runnable {

    BlockingQueue<Integer> storage;
    boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "被生产了");
                    // 程序被阻塞在这里，无法结束
                    storage.put(num);
                }
                ++num;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("停止运行");
        }
    }

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.storage = blockingQueue;
    }
}

class Consumer {

    BlockingQueue<Integer> storage;

    public boolean needMoreNums() {
        return Math.random() < 0.96;
    }

    public Consumer(BlockingQueue<Integer> queue) {
        this.storage = queue;
    }
}