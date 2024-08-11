package com.wallet.tutor.module09;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Try to get default value using 
 * - opt.orElse(0);
 * - opt.orElseThrow
 * - opt.orElseGet
 * Add some elements to the array.
 *
 */
@Slf4j
public class OptionalTutor {

    public static void main(String... args) {
        
        List<Integer> list = Arrays.asList();
        
        Optional<Integer> optional = list.stream().reduce(Integer::max);

        Integer zero = optional.orElse(0);

        Supplier<Integer> supplier = () -> 100;

        Integer aDefault = optional.orElseGet(supplier);

        log.info("max = " + optional);

        log.info("max = " + zero);

        log.info("max = " + aDefault);

        Integer integer = optional.orElseThrow(()-> new NullPointerException());
    }
}
