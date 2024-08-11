package com.wallet.bank.email;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

@Slf4j
public class EmailService implements Runnable, Serializable {

    @Serial
    private static final long serialVersionUID = -6872857384878095572L;
    private final Queue queue = new Queue();
    private boolean closed;
    @Getter
    private int sentEmails = 0;

    public EmailService() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        Email email;
        while (true) {
            if (closed) {
                return;
            }

            if ((email = queue.get()) != null) {
                sendEmail(email);
            }
            try {
                synchronized (queue) {
                    queue.wait();
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                return;
            }

        }
    }

    private void sendEmail(Email email) {
        log.info(email.getBody());
        sentEmails++;
    }

    public void sendNotificationEmail(Email email) throws EmailException {
        if (!closed) {
            queue.add(email);
            synchronized (queue) {
                queue.notify();
            }
        } else
            throw new EmailException("Mailbox is closed!");
    }

    public void close() {
        closed = true;
        synchronized (queue) {
            queue.notify();
        }
    }
}
