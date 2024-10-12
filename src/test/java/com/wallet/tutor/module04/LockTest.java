package com.wallet.tutor.module04;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static org.junit.jupiter.api.Assertions.fail;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
/*
 * When you synchronize threads t1 and t2 should output string in pairs: 11,22,11,22,22,11,
 * And the thread should print strings only when the job is over,
 * i.e. there should be "," in the end of work of each stream.
 * What's wrong in this example? Try to fix it.
 */
@Slf4j
public class LockTest {
    Thread t1, t2, t3;
    Object monitor = new Object();
    int runningThreadNumber = 1;
    StringBuffer stringBuilder = new StringBuffer("");
    public static long ITERATIONS = 1000000;

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
            synchronized (monitor) {
                for (int i = 0; i < ITERATIONS; i++) {
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
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                Thread.yield();
            }
        }
    }

    @Test
    public void testThread() {
        long start = new Date().getTime();
        t1 = new Thread(new WritingThread("1", new ReentrantLock()));
        t2 = new Thread(new WritingThread("2", new ReentrantLock()));
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
        log.info("Time of work: " + time);

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
