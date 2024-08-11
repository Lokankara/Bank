package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Consumer implements Runnable {
    protected BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            log.info(queue.take());
            log.info(queue.take());
            log.info(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}