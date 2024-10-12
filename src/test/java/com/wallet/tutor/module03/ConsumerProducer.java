package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerProducer {
    private int count;
    private String message;
    private boolean request;
    private final Object monitor = new Object();

    public ConsumerProducer(String message, boolean request) {
        this.message = message;
        this.request = request;
    }

    boolean waitingThreadCanTryAgain = false;

    public synchronized void consume() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        count--;
    }

    public synchronized void produce() {
        count++; // produce
        notifyAll(); // notify waiting threads to resume
    }

    public synchronized String retrieveMessage() {
        while (request == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        request = false;
        return message;
    }

    public synchronized void storeMessage(String message) {
        this.message = message;
        request = true;
        notify();
    }

    public synchronized void waitAndNotify() {

        synchronized (monitor) {
            while (!waitingThreadCanTryAgain) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        synchronized (monitor) {
            waitingThreadCanTryAgain = true;
            monitor.notify();
        }
    }
}