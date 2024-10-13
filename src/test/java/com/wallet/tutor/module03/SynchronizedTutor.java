package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SynchronizedTutor {

    Integer counter = 0;

    final Object lock = new Object();

    class TestThread implements Runnable {
        private final String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }


        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        }
    }

    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<>();
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

//		log.info(buf);
        log.info("counter = " + counter);
        assertEquals(1000000, (int) counter);
    }
}
