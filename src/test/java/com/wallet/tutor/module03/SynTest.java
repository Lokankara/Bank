package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SynTest implements Runnable {
    private int balance;

    public void run() {
        for (int i = 0; i < 50; i++) {
            increment();
            log.info("balance is " + balance);
        }
    }

    public void increment() {
        synchronized (this) {
            int i = balance;
            balance = i + 1;
        }
    }
}