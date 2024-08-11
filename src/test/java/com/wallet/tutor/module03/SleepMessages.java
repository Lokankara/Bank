package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepMessages {
    public static void main(String args[])
            throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        for (int i = 0; i < importantInfo.length; i++) {
            Thread.sleep(1000);
            log.info(importantInfo[i]);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
