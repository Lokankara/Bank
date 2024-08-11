package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class WorkerThread extends Thread {

    /**
     * Try to set daemon flag to true or false and look
     * how the behavior will be changing.
     */
    public WorkerThread() {
        setDaemon(true);
    }

    public void run() {
        int count = 0;
        while (true) {
            log.info("Hello from Worker " + count++);
            try {
                sleep(5000);
            } catch (InterruptedException e) {

            }
        }
    }
}