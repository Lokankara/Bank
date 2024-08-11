package com.wallet.tutor.module04;

public class Increment {
    private final ReadWriteLockTest lock = new ReadWriteLockTest();
    private int value;

    public void incrementValue() {
        lock.writeLock().lock();
        try {
            value++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getValue() {
        lock.readLock().lock();
        try {
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }
}
