package com.wallet.tutor.module08.defaultInterfaces;

import java.util.Objects;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
    static <T> Function<T, T> identity() {
        return t -> t;
    }
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
}
