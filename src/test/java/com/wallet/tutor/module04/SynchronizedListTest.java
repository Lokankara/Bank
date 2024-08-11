package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/*
 *  Why does the program (periodically) fail with ArrayIndexOutOfBoundException?
 *  What should you do to prevent this happening?
 *  An exception is not always thrown, you might need to run the program multiple times.
 */

@Slf4j
public class SynchronizedListTest {
    Object monitor = new Object();
    static boolean failed = false;
    static StringBuilder buf = new StringBuilder();

    static void log(String s) {
        buf.append(s).append("\n");
    }

    static void err(String s) {
        buf.append("<span style='color:red'><b>" + s + "</b></span>\n");
        failed = true;
    }

    static String[] animals =
            {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};
    List<String> randomAnimals = new ArrayList<>();

    public String getRandomAnimal() {
        int index = (int) (Math.random() * animals.length);
        return animals[index];
    }

    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 50000; i++) {
                        randomAnimals.add(getRandomAnimal());
                    }
                } catch (Exception e) {
                    err(e.getClass().getName());
                }
            }
        }
    }

    public void print(Collection<?> c) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next())
                    .append(" ");
        }
        log(builder.toString());
    }

    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new TestThread("t" + i)));
        }
        log.info("Starting threads");
        for (int i = 0; i < 100; i++) {
            threads.get(i).start();
        }
        log.info("Waiting for threads");
        try {
            for (int i = 0; i < 100; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        assertFalse(failed);
    }
}
