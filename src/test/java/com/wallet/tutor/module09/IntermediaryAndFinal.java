package com.wallet.tutor.module09;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
public class IntermediaryAndFinal {
    
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("one", "two", "three", "four", "five");
        
        Predicate<String> p1 = Predicate.isEqual("two");
        Predicate<String> p2 = Predicate.isEqual("three");
        
        List<String> list = new ArrayList<>();
        
        stream
                .peek(System.out::println)
                .filter(p1.or(p2))
                .forEach(list::add);
        
        log.info("Done!");
        log.info("size = " + list.size());
    }
}
