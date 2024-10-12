package com.wallet.tutor.module09;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class RandomTutor {

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> intList = random
                .ints()
                .limit(10)
                .boxed()
                .toList();

        int[] ints = random
                .ints()
                .limit(10)
                .toArray();

        IntStream intStream = Arrays.stream(ints);

        String joined = intStream
                .boxed()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        intStream = Arrays.stream(ints);

        log.info(joined);
        log.info(String.valueOf(intStream.average().getAsDouble()));
        log.info(String.valueOf(intList.stream()
                .mapToInt(Integer::intValue)
                .sum()));
    }
}
