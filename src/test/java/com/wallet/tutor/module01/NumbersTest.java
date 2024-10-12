package com.wallet.tutor.module01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class NumbersTest {

    Double d = 123d;
    Integer i = 23545452;

    @Test
    public void testConvertNumbers() {
        log.info("=== testConvertNumbers() ===");
        covertNumbers(i);
        covertNumbers(123d);
        covertNumbers(1239879634342l);
    }

    public void covertNumbers(Number number) {
        //TODO void covertNumbers(Number number)
    }

    public void stringToNumber(String s) {
        log.info("=== Convert string " + s + " to Number ===");

        String message = "Exception Format: " + s;

        try {
            byte parseByte = Byte.parseByte(s);
            log.info(String.format("Byte: %s", parseByte));
        } catch (NumberFormatException e) {
            log.info(message);
        }
        try {
            short parseShort = Short.parseShort(s);
            log.info(String.format("Short: %s", parseShort));
        } catch (NumberFormatException e) {
            log.info(message);
        }
        try {
            int parseInt = Integer.parseInt(s);
            log.info(String.format("Integer: %d", parseInt));
        } catch (NumberFormatException e) {
            log.info(message);
        }
        try {
            long parseLong = Long.parseLong(s);
            log.info(String.format("Long: %d", parseLong));
        } catch (NumberFormatException e) {
            log.info(message);
        }
        try {
            float parseFloat = Float.parseFloat(s);
            log.info(String.format("Float: %s", parseFloat));
        } catch (NumberFormatException e) {
            log.info(message);
        }
        try {
            double parseDouble = Double.parseDouble(s);
            log.info(String.format("Double: %s", parseDouble));
        } catch (NumberFormatException e) {
            log.info(String.format("%s%s", message, s));
        }
    }

    @Test
    public void testStringToNumber() {
        stringToNumber("123");
        stringToNumber("-123");
        stringToNumber("12345678987654321");
        stringToNumber("1.11f");
        stringToNumber("1.1111111111");
    }

    /**
     * Getting  NaN value for Double type
     */
    @Test
    public void testIsNaN() {
        assertTrue(Double.isNaN(Math.sqrt(-4)));
    }

    /**
     * Getting Infinite value for Double type
     */
    @Test
    public void testIsInfinite() {
        assertTrue(Double.isInfinite(Double.MAX_VALUE * 2));
    }
}
