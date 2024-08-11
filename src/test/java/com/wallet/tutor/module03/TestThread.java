package com.wallet.tutor.module03;

class TestThread extends Thread {
    // this is shared data visible for both threads
    TestThread t = new TestThread();
    Object monitor = new Object();
    boolean shouldTerminate = false;
// we want to interrupt the thread in another thread:

    public void run() {
        shouldTerminate = true;
        t.interrupt();
        while (true) {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    if (shouldTerminate) return;
                }
            }
            if (shouldTerminate) return;
        }
    }

}

