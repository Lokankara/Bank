package com.wallet.tutor.module09;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
@Slf4j
public class FlatMapExample {

    public static void main(String... args) {
        
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> list2 = Arrays.asList(2, 4, 6);
        List<Integer> list3 = Arrays.asList(3, 5, 7);
        
        List<List<Integer>> list = Arrays.asList(list1, list2, list3);
        
        Function<List<?>, Integer> size = List::size;
        Function<List<Integer>, Stream<Integer>> flatmapper = Collection::stream;

        list.stream()
                .flatMap(flatmapper)
                .forEach(System.out::print);
    }
}
