package com.wallet.tutor.module08.defaultInterfaces;

@FunctionalInterface
public interface PersonPredicate<T> {
    boolean test(T t);

    static <T> java.util.function.Predicate<T> toJavaPredicate(PersonPredicate<T> customPersonPredicate) {
        return customPersonPredicate::test;
    }
}
