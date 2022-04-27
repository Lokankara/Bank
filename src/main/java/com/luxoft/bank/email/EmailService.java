package com.luxoft.bank.email;

import java.io.Serial;
import java.io.Serializable;

public class EmailService implements Runnable, Serializable {

    @Serial
    private static final long serialVersionUID = -6872857384878095572L;
    private final Queue queue = new Queue();
    private boolean closed;
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

    public int getSentEmails() {
        return sentEmails;
    }

    private void sendEmail(Email email) {
        System.out.println(email);
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
