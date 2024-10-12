package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * Experiment with DeadlockTutor.
 * How the deadlock can be fixed?
 * To solve this problem, copy it into your development environment (Eclipse/IDEA).
 * When you get the deadlock you should manually stop program execution (in Eclipse use a Stop button in Debug view).
 */

@Slf4j
public class DeadlockTest {
    static StringBuffer buf = new StringBuffer();
    Thread t1;
    Thread t2;
    Account account1 = new Account(100);
    Account account2 = new Account(50);

    static class Account {
        double balance;
        int id;

        public Account(double balance) {
            this.balance = balance;
        }

        void withdraw(double amount) {
            balance -= amount;
        }

        void deposit(double amount) {
            balance += amount;
        }

        void transfer(Account from, double amount) {
            // block the current account
            synchronized (this) {
                deposit(amount);
            }
            // block the account, from which transfer is done
            from.withdraw(amount);
        }
    }

    @Test
    public void testDeadlock() {
        t1 = new Thread(() -> IntStream.range(0, 200).forEach(i -> {
            account1.transfer(account2, 30);
            log.info("t1: " + i);
            Thread.yield();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        t2 = new Thread(() -> IntStream.range(0, 200).forEach(i -> {
            account2.transfer(account1, 30);
            log.info("t2: " + i);
        }));

        log.info("Starting threads");
        t1.start();
        t2.start();

        log.info("Waiting for threads");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(buf.toString());
    }
}
