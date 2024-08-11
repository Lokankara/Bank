package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
@Slf4j
public class CyclicBarrierTest {
    Thread t1, t2, t3;
    CyclicBarrier barrier = new CyclicBarrier(3);

    static StringBuffer buf = new StringBuffer();

    static void log(String s) {
        buf.append(s + "\n");
    }

    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                log(threadName + ":" + i);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testThread() {
        t1 = new Thread(new TestThread("t1"));
        t2 = new Thread(new TestThread("t2"));
        t3 = new Thread(new TestThread("t3"));
        log.info("Starting threads");
        t1.start();
        t2.start();
        t3.start();

        log.info("Waiting for threads");
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.valueOf(buf));
    }
}
