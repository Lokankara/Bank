package com.wallet.tutor.module09;

import com.wallet.tutor.Logger;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateTutor {

    /**
     * Find only elements with length > 3, which are equal to "two" or "three"
     * using predefined predicates.
     * <p>
     * Program should print "three"
     */
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("one", "two", "three", "four", "five");

        Predicate<String> p1 = s -> s.length() > 3;

        Predicate<String> p2 = Predicate.isEqual("two");

        Predicate<String> p3 = Predicate.isEqual("three");

        List<String> collect = stream.filter(p1.and(p2.or(p3))).collect(Collectors.toList());

        Logger.log(collect);

    }
}
