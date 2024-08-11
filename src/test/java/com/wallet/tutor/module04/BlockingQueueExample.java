package com.wallet.tutor.module04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueExample {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(String.valueOf(consumer)).start();
        Thread.sleep(4000);

        BlockingDeque<String> deque =
                new LinkedBlockingDeque<String>();
        deque.addFirst("1");
        deque.addLast("2");
        String two = deque.takeLast();
        String one = deque.takeFirst();
    }
}
