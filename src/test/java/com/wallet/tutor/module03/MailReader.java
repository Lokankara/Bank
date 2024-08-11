package com.wallet.tutor.module03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailReader extends Thread {
    private Mailbox myMailbox;

    public MailReader(Mailbox box) {
        this.myMailbox = box;
    }

    public void run() {
        while (true) {
            if (myMailbox.request) {
                log.info(myMailbox.message);
                myMailbox.request = false;
            }
            try {
                sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

    class Mailbox {
        public boolean request;
        public String message;

        public synchronized String storeMessage() {
            request = false;
            return message;
        }

        public synchronized String retrieveMessage() {
            request = false;
            return message;
        }
    }
}
