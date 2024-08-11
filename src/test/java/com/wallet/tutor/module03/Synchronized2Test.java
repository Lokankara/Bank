package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Synchronized2Test {

    static StringBuffer buf = new StringBuffer();
    Integer counter = 0;
    Object monitor = new Object();

    static void log(String s) {
        buf.append(s).append("\n");
    }

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
                log(threadName + ":" + i + ":" + counter);
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
            e.printStackTrace();
        }
        log.info(String.valueOf(buf));
        log.info("counter = " + counter);
        assertEquals(1000000, (int) counter);
    }
}
