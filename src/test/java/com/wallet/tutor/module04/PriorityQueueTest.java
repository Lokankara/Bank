package com.wallet.tutor.module04;

import java.util.concurrent.PriorityBlockingQueue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Implement the compareTo() method in order. Print orders in ReadOrderThread so that order with priority==true is the first.
 */

@Slf4j
public class PriorityQueueTest {
    static StringBuffer buf = new StringBuffer();

    PriorityBlockingQueue<Order> orderQueue = new PriorityBlockingQueue<>();

    class Order implements Comparable<Order> {
        public String title;
        public boolean priority;

        @Override
        public String toString() {
            return "Order " + title + ", priority=" + priority;
        }

        public Order(String title, boolean priority) {
            this.title = title;
            this.priority = priority;
        }

        @Override
        public int compareTo(Order o) {

            return o.priority ? 1 : -1;
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class AddOrderThread implements Runnable {
        @Override
        public void run() {
            orderQueue.put(new Order("books", false));
            sleep(10);
            orderQueue.put(new Order("table", false));
            sleep(10);
            orderQueue.put(new Order("computer", true));
            sleep(10);
            orderQueue.put(new Order("dog", false));
            log.info(String.valueOf(orderQueue));
        }
    }

    class ReadOrderThread implements Runnable {
        int orderNum = 0;

        @Override
        public void run() {
            sleep(100);
            while (orderNum < 4) {
                try {
                    Order order = orderQueue.take();
                    // check that first taken order has priority==true
                    if (order.priority && orderNum == 0) {
                        priorityAhead = true;
                    }
                    log(order.toString());
                    orderNum++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void log(String s) {
        buf.append(s + "\n");
    }

    boolean priorityAhead = false;

    @Test
    public void testName() throws Exception {
        Thread addOrderThread = new Thread(new AddOrderThread());
        Thread readOrderThread = new Thread(new ReadOrderThread());
        addOrderThread.start();
        /**
         * TODO: we should wait while orders will appear in the list,
         * otherwise we will read orders in order it was added to the queue
         */
        readOrderThread.start();
        addOrderThread.join();
        readOrderThread.join();
        assertTrue(priorityAhead);
    }
}
