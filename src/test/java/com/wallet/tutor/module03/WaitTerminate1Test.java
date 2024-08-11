package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * One solution to use thread interruption.
 * We can measure some time for a work of the thread, and then kill the thread if it has not finished.
 * In this code you can find a separate terminate thread, which runs for 10 ms and interrupts thread t1.
 * Also, there could be used some other indicator to interrupt a thread - for example, on pressing Interrupt button in GUI.
 * The solution with the use of shouldTerminate flag is not suitable: the thread is hanging in method wait(),
 * and it doesn't check for shouldTerminate flag. It can be terminated only by using of Thread.interrupt().
 */
@Slf4j
public class WaitTerminate1Test {
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
                            //log("wait for thread "+"t"+runningThreadNumber);
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        log("INTERRUPTED EXCEPTION OCCURED!..");
                        return;
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
                testThread2.shouldTerminate = true;
            }
        });
        terminator.start();

        /**
         * In 10 ms will be executed interruptor, which will stop t1.
         */
        Thread interruptor = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                    log("Interrupting thread t1...");
                    t1.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        interruptor.start();

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
