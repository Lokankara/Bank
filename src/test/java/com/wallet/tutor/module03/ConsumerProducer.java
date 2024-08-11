package com.wallet.tutor.module03;

public class ConsumerProducer {
    private int count;
    private String message;
    private boolean request;

    public ConsumerProducer(String message, boolean request) {
        this.message = message;
        this.request = request;
    }

    // monitor - any SHARED object
    // of waiting and notifier threads
    Object monitor = new Object();
    boolean waitingThreadCanTryAgain = false;

    public synchronized void consume() {
// keep waiting if nothing is produced to consume
        while (count == 0) {
            try {
                wait();
                // give up lock and wait
            } catch (InterruptedException e) {
// keep trying
            }
        }
        count--; // consume
    }

    public synchronized void produce() {
        count++; // produce
        notifyAll(); // notify waiting threads to resume
    }

    public synchronized String retrieveMessage() {
// keep waiting if nothing is produced to consume
        while (request == false) {
            try {
                wait(); // give up lock and wait
            } catch (InterruptedException e) {
// keep trying
            }
        }
        request = false; // consume
        return message;
    }

    public synchronized void storeMessage(String message) {
        this.message = message; // produce
        request = true;
        notify(); // notify waiting threads to resume
    }

    public synchronized void waitAndNotify() {

        // waiting thread code:
        synchronized (monitor) {
            while (!waitingThreadCanTryAgain) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

// notifier thread code:
        synchronized (monitor) {
            waitingThreadCanTryAgain = true;
            monitor.notify();
        }
    }
}