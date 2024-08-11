package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemonTest {
    public static void main(String[] args) {
        new WorkerThread().start();
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info("Main Thread ending");
    }
}
