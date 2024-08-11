package com.wallet.tutor.module08.defaultInterfaces;

@FunctionalInterface
public interface Comparator<T> {
    int compare(T a, T b);
}