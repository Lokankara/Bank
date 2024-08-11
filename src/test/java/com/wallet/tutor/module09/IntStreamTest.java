package com.wallet.tutor.module09;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class IntStreamTest {

    public static IntStream intStream() {
        return IntStream.of(2, 3, 3, 4);
    }

    public static void log(int i) {
        log.info(String.valueOf(i));
    }

    public static void log(String i) {
        log.info(i);
    }


    /**
     * Find and print:
     * - max number in intStream
     * - average number in intStream
     * - list of distinct values: "2,3,4" (use distinct())
     */
    @Test
    public void testIntStream() {

        int max = intStream().max().orElse(0);
        log(max);
        assertEquals(max, 4);

        int avg = (int) intStream().average().orElse(0);
        log(avg);
        assertEquals(avg, 3);
        String distinct = intStream().distinct().mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        log(distinct);
        assertEquals(distinct, "2,3,4");

    }
}
