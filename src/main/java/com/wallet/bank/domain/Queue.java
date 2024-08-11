package com.wallet.bank.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue<T> implements Serializable {
    private final List<T> list = Collections.synchronizedList(new LinkedList<>());

    public void add(T t) {
        list.add(t);
    }

    public T get() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(0);
    }
}
