package com.wallet.tutor.module03;

import static org.junit.jupiter.api.Assertions.assertFalse;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Complicated task: JUBILEE
 * Add another one stream which should put messages about "jubilee" values of the counter to the log, multiple of 10,
 * for example 10, 20, 30...
 * Jubilee messages should be printed after all threads overcome the jubilee, but before any stream goes forward.
 */

@Slf4j
public class WaitTutorJubileeTest {
    static StringBuffer buf = new StringBuffer();

    static void log(String s) {
        buf.append(s + "\n");
    }

    Thread t1, t2, t3;
    Object monitor = new Object();
    Object jubileeMonitor = new Object();
    int runningThreadNumber = 1;
    int t1Counter = 0, t2Counter = 0;
    volatile int waitingThreads = 0;

    class JubileeThread implements Runnable {
        public void run() {
            while (true) {
                if (waitingThreads == 2) {
                    logAndCheckJubilee("t3", counter);
                    synchronized (jubileeMonitor) {
                        waitingThreads = 0;
                        jubileeMonitor.notifyAll();
                    }
                }
            }
        }
    }

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
                                log("t1 is ahead with i=" + i + ", wait for t2Counter = " + t2Counter);
                                monitor.wait();

                            }
                        }
                        if (n == 2) {
                            if (i > t1Counter) {
                                log("t2 is ahead with i=" + i + ", wait for t1Counter = " + t1Counter);
                                monitor.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logAndCheckCounter(threadName, i);
                }

                Thread.yield();

                if (i % 10 == 9) {
                    synchronized (jubileeMonitor) {
                        waitingThreads++;
                        try {
                            jubileeMonitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testThread() {
        t1 = new Thread(new TestThread("t1", 1));
        t2 = new Thread(new TestThread("t2", 2));
        t3 = new Thread(new JubileeThread());
        log.info("Starting threads");
        t1.start();
        t2.start();
        t3.start();

        log.info("Waiting for threads");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(buf));
        assertFalse(wrongCounter);
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
        log(threadName + ":" + c);
        if (counter != c) {
            wrongCounter = true;
        }
        counterOccured++;

        if (counterOccured == threadsAmount) {
            counter++;
            counterOccured = 0;
        }
    }

    private void logAndCheckJubilee(String threadName, int c) {
        log("JUBILEE, " + threadName + " = " + c);
    }

}
