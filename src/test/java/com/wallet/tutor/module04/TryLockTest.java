package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test will fail if executing for too long time.
 * Use lock.tryLock() to increase the speed of the lock.
 * Also try to use tryLock(time, timeUnit) to increase the execution time even more (try different timings).
 */
@Slf4j
public class TryLockTest {
    Thread t1, t2, t3;
    Object monitor = new Object();
    int runningThreadNumber = 1;
    StringBuffer stringBuilder = new StringBuffer("");
    public static long ITERATIONS = 100000;

    static StringBuffer buf = new StringBuffer();

    class WritingThread implements Runnable {
        String threadName;
        Lock lock;

        public WritingThread(String threadName, Lock lock) {
            this.threadName = threadName;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < ITERATIONS; i++) {
                    if (lock.tryLock()) {
                        lock.lock();
                        stringBuilder.append(threadName);
                        Thread.yield();
                        stringBuilder.append(threadName);
                        Thread.yield();
                        stringBuilder.append(",");
                        lock.unlock();
                    }
                }
            }
        }
    }

    class ReadingThread implements Runnable {
        String threadName;
        Lock lock;

        public ReadingThread(String threadName, Lock lock) {
            this.threadName = threadName;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                String s = stringBuilder.toString();
                int len = s.length();
                int l = len > 50 ? len - 50 : 0;
                log.info(len + ":" + s.substring(l));
                lock.unlock();
                Thread.yield();
            }
        }
    }

    @Test
    public void testThread() {
        long start = new Date().getTime();
        Lock lock = new ReentrantLock();
        t1 = new Thread(new WritingThread("1", lock));
        t2 = new Thread(new WritingThread("2", lock));
        t3 = new Thread(new ReadingThread("3", new ReentrantLock()));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        long time = new Date().getTime() - start;
        log.info("Time of work:" + time);

        String[] lines = buf.toString().split("\n");
        for (int i = 0; i < lines.length - 1; i++) {
            String line = lines[i];
            String[] groups = line.split(",");
            for (int j = 1; j < groups.length - 1; j++) {
                if (!groups[j].equals("11") && !groups[j].equals("22")) {
                    fail("String is not paired: " + lines[i]);
                    break;
                }
            }
        }
    }
}
