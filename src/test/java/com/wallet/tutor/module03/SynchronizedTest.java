package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Put the contents of the run() method in a synchronized (counter) {...} block.
 * Did we make counter growing consistently? Why? \
 * Find the mistake or look at SynchronizedTutor2 (run several times if the test produces a correct result).
 */
@Slf4j
public class SynchronizedTest {

    private final Object monitor = new Object();
    private Integer counter = 0;

    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                synchronized (monitor) {
                    counter++;
                    Thread.yield();
                }
            }
        }
    }

    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new TestThread("t" + i)));
        }
        log.info("Starting threads");
        for (int i = 0; i < 1000; i++) {
            threads.get(i).start();
        }
        log.info("Waiting for threads");
        try {
            for (int i = 0; i < 1000; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info("counter = " + counter);
    }
}
