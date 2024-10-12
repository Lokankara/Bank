package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Another solution is to use a timeout in the method wait(), so that the thread is not waiting forever, but get interrupted after the timeout,
 * and check the counter waiting cycles (or some flag).
 */
@Slf4j
public class WaitTerminate2Test {
    static StringBuffer buf = new StringBuffer();

    Thread t1, t2;
    Object monitor = new Object();
    int runningThreadNumber = 1;

    class TestThread implements Runnable {
        String threadName;
        public boolean shouldTerminate;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; ; i++) {
                log.info(threadName + ":" + i);
                synchronized (monitor) {
                    try {
                        int waitingCycle = 0;
                        while (!threadName.equals("t" + runningThreadNumber)) {
                            if (waitingCycle > 0)
                                log.info("wait for thread t" + runningThreadNumber + ", waiting cycle:" + waitingCycle);
                            monitor.wait(100);
                            if (waitingCycle++ >= 10) {
                                log.info("EXIT from " + threadName + ": too long waiting...");
                                return;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runningThreadNumber++;
                    if (runningThreadNumber > 2) {
                        runningThreadNumber = 1;
                    }

                    monitor.notifyAll();
                    if (shouldTerminate) {
                        log.info("TERMINATED " + threadName + "!");
                        return;
                    }
                }
            }
        }
    }

    @Test
    public void testThread() {
        final TestThread testThread1 = new TestThread("t1");
        t1 = new Thread(testThread1);
        final TestThread testThread2 = new TestThread("t2");
        t2 = new Thread(testThread2);
        log.info("Starting threads...");
        t1.start();
        t2.start();

        Thread terminator = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testThread2.shouldTerminate = true;
            }
        });
        terminator.start();

        log.info("Waiting threads to join...");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }


}
