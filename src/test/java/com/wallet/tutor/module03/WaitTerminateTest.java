package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * How to stop the thread?
 * To interrupt the thread, we can use the flag shouldTerminate, which should be checked in a loop inside the run() method.
 * If the flag becomes true, we just exit the loop.
 * However, there could be problems if there are some threads which depends on terminated thread.
 * Try commenting out the termination of the thread 2. Then thread 1 will hang, waiting for the second stream forever...
 * What are the possible solutions?
 */

@Slf4j
public class WaitTerminateTest {
    static StringBuffer buf = new StringBuffer();

    static void log(String s) {
        buf.append(s + "\n");
    }

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
                log(threadName + ":" + i);
                synchronized (monitor) {
                    try {
                        while (!threadName.equals("t" + runningThreadNumber)) {
                            log("wait for thread t" + runningThreadNumber);
                            monitor.wait();
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
                        log("TERMINATED " + threadName + "!");
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
        log("Starting threads...");
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
                testThread1.shouldTerminate = true;
                // comment this line out and execute
                testThread2.shouldTerminate = true;
            }
        });
        terminator.start();

        log("Waiting threads to join...");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(buf));
    }
}
