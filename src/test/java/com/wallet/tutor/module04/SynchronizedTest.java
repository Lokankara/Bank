package com.wallet.tutor.module04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Here the problem from SynchronizedListTutor is solved by using of Collections.synchronizedList.
 * However, here we're collecting all list items into StringBuffer in print() method.
 * Why ConcurrentModificationException is thrown? That happens because the list is changing in run() while we're iterating over it in print().
 * What to do to prevent this from happening?
 * - Make the print() method synchronized - does not help
 * - Place the add () and print() in synchronized block - it helps, but slow down
 * - Use CopyOnWriteArrayList - it helps
 * - Pass Collections.unmodifiableList() to method print() - does not help
 * - Pass copy of ArrayList to print() method - it helps
 * Solve this problem by passing a copy of list to print() - you should use constructor of ArrayList.
 * Then print() will be working with its own copy of list, which will not be changing.
 */
public class SynchronizedTest {

    Object monitor = new Object();
    static boolean failed = false;
    static StringBuffer buf = new StringBuffer();

    static void err(String s) {
        buf.append("<span style='color:red'><b>" + s + "</b></span>\n");
        failed = true;
    }

    static String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};
    List<String> randomAnimals = Collections.synchronizedList(new ArrayList<>());

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
            try {
                for (int i = 0; i < 20; i++) {
                    synchronized (monitor) {
                        randomAnimals.add(getRandomAnimal());
                        print(randomAnimals);
                    }
                    print(new ArrayList<>(randomAnimals));
                }
            } catch (Exception e) {
                err(e.getClass().getName());
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
    }

    @Test
    public void testThread() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new TestThread("t" + i)));
        }
        for (int i = 0; i < 100; i++) {
            threads.get(i).start();
        }
        try {
            for (int i = 0; i < 100; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(failed);

    }
}
