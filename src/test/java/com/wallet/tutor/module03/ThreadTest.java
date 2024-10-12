package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadTest {

    static class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                log.info(threadName + ":" + i);
                Thread.yield();
            }
        }
    }

    @Test
    public void testThread() throws InterruptedException {
        Thread t1 = new Thread(new TestThread("t1"));
        Thread t2 = new Thread(new TestThread("t2"));
        log.info("Starting threads");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Finished");
    }
}
