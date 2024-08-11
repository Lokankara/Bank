package com.wallet.tutor.module09;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class ChainConsumers {

    public static void main(String... args) {
        
        List<String> strings = 
                Arrays.asList("one", "two", "three", "four", "five");
        
        List<String> result = new ArrayList<>();
        
        Consumer<String> c1 = System.out::println;
        
        strings.forEach(c1.andThen(result::add));

        log.info("size of result = " + result.size());
        
        strings.forEach(e->{log.info(e+", ");});
        
        log.info(String.join(", ", strings));
    }
}
