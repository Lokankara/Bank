package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Here you can find a solution for WaitTutor: in cases when one thread goes forward, the second is waiting.
 * However, this code still needs a small fix - please find out.
 */
@Slf4j
public class CheckCounterWaitTest {
    Thread t1, t2;
    private final Object monitor = new Object();
    int t1Counter = 0, t2Counter = 0;

    class TestThread implements Runnable {
        String threadName;
        int n;

        public TestThread(String threadName, int n) {
            this.threadName = threadName;
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                logAndCheckCounter(threadName, i);
                synchronized (monitor) {
                    if (n == 1) {
                        t1Counter = i;
                    }
                    if (n == 2) {
                        t2Counter = i;
                    }

                    monitor.notify();
                    Thread.yield();
                    try {
                        if (n == 1) {
                            if (i > t2Counter) {
                                monitor.wait();
                            }
                        }
                        if (n == 2) {
                            if (i > t1Counter) {
                                monitor.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }

                }
                Thread.yield();
            }
        }
    }

    @Test
    public void testThread() {
        t1 = new Thread(new TestThread("t1", 1));
        t2 = new Thread(new TestThread("t2", 2));
        log.info("Starting threads");
        t1.start();
        t2.start();

        log.info("Waiting for threads");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * This code to check for the correctness of next counter.
     * Counters should be ordered: 0, 0, 1, 1, 2, 2, etc.
     */
    boolean wrongCounter = false;
    int counter = 0;
    static final int threadsAmount = 2;
    int counterOccured = 0;

    private void logAndCheckCounter(String threadName, int c) {
        log.info(threadName + ":" + c);
        if (counter != c) {
            wrongCounter = true;
        }
        counterOccured++;

        if (counterOccured == threadsAmount) {
            counter++;
            counterOccured = 0;
        }
    }
}
