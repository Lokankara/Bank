package com.wallet.tutor.module04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Why counter shows different values (rerun if jUnit passed successfully)?
 * How to fix this?
 * Use AtomicInteger instead of int for counter variable. Use method getAndIncrement() to increment counter.
 */
@Slf4j
public class AtomicCounterTest {
    final int ITERATIONS = 1000000;
    //	volatile int counter=0;
    AtomicInteger counter = new AtomicInteger();


    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                counter.incrementAndGet();
            }

        }
    }

    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new TestThread("t" + i)));
        }
        log.info("Starting threads");
        for (int i = 0; i < 100; i++) {
            threads.get(i).start();
        }
        try {
            for (int i = 0; i < 100; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Counter=" + counter);

        assertTrue(counter.get() == ITERATIONS * 100);
    }
}
