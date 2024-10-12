package com.wallet.tutor.module09;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
public class PredicateTutor {

    /**
     * Find only elements with length > 3, which are equal to "two" or "three"
     * using predefined predicates.
     * Program should print "three"
     */
    @Test
    public void testMain() {
        Stream<String> stream = Stream.of("one", "two", "three", "four", "five");
        Predicate<String> p1 = s -> s.length() > 3;
        Predicate<String> p2 = Predicate.isEqual("two");
        Predicate<String> p3 = Predicate.isEqual("three");
        List<String> collect = stream.filter(p1.and(p2.or(p3))).toList();
        log.info(collect.toString());
    }
}
