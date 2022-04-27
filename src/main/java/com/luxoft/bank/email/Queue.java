package com.luxoft.bank.email;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue implements Serializable {

    @Serial
    private static final long serialVersionUID = -367534955230149744L;

    private final List<Email> emails =
            Collections.synchronizedList(new LinkedList<>());

    public void add(Email email) {
        emails.add(email);
    }

    public Email get() {
        if (emails.size() > 0) {
            return emails.remove(emails.size() - 1);
        }
        return null;
    }

}
