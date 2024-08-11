package com.wallet.tutor.module04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private Lock lock = new ReentrantLock();
    private int count = 0;

    public int inc() {
        int newCount;
        try {
            lock.lock();
            newCount = ++count;
        } finally {
            lock.unlock();
        }
        return newCount;
    }

}