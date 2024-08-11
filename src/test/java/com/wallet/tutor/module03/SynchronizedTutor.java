package com.wallet.tutor.module03;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SynchronizedTutor {

    static StringBuilder buf = new StringBuilder();
    Integer counter = 0;

    final Object lock = new Object();

    static void log(String s) {
        buf.append(s).append("\n");
    }

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
                    log(threadName + ":" + i + ":" + counter);
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
        log("Starting threads");
        for (int i = 0; i < 1000; i++) {
            threads.get(i).start();
        }
        log("Waiting for threads");
        try {
            for (int i = 0; i < 1000; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//		log(buf);
        log("counter = " + counter);
        assertEquals(1000000, (int) counter);
    }
}
