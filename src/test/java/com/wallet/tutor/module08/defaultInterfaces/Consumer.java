package com.wallet.tutor.module08.defaultInterfaces;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
