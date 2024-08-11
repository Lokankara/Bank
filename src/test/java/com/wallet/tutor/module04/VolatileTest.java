package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Volatile allows you to write data to a stream, without using a thread buffer.
 * Try to add volatile as flag - and each thread will have its own value of running, and the thread will terminate.
 */
@Slf4j
public class VolatileTest {
    // Variable running should be volatile
    volatile boolean running = true;

    static StringBuffer buf = new StringBuffer();

    static void log(String s) {
        buf.append(s + "\n");
    }

    @Test
    public void testVolatile() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                while (running) {
                    counter++;
                }
                log("Thread 1 finished. Counted up to " + counter);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                log("Thread 2 finishing");
                running = false;
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(buf));
    }
}
