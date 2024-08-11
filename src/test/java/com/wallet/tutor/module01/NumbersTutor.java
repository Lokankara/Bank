package com.wallet.tutor.module01;


import static com.wallet.tutor.Logger.log;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NumbersTutor {

    Double d = 123d;
    Integer i = 23545452;

    @Test
    public void testConvertNumbers() {
        log("=== testConvertNumbers() ===");
        covertNumbers(i);
        covertNumbers(123d);
        covertNumbers(1239879634342l);
    }

    /**
     * Conversion between numeric data types
     *
     * @param number
     */
    public void covertNumbers(Number number) {
        //TODO void covertNumbers(Number number)
    }

    /**
     * Conversion from String type to various numeric data types
     *
     * @param s
     */
    public void stringToNumber(String s) {
        log("=== Convert string " + s + " to Number ===");

        String message = "Exception Format: " + s;

        try {
            byte parseByte = Byte.parseByte(s);
            log(String.format("Byte: %s", parseByte));
        } catch (NumberFormatException e) {
            log(message);
        }
        try {
            short parseShort = Short.parseShort(s);
            log(String.format("Short: %s", parseShort));
        } catch (NumberFormatException e) {
            log(message);
        }
        try {
            int parseInt = Integer.parseInt(s);
            log(String.format("Integer: %d", parseInt));
        } catch (NumberFormatException e) {
            log(message);
        }
        try {
            long parseLong = Long.parseLong(s);
            log(String.format("Long: %d", parseLong));
        } catch (NumberFormatException e) {
            log(message);
        }
        try {
            float parseFloat = Float.parseFloat(s);
            log(String.format("Float: %s", parseFloat));
        } catch (NumberFormatException e) {
            log(message);
        }
        try {
            double parseDouble = Double.parseDouble(s);
            log(String.format("Double: %s", parseDouble));
        } catch (NumberFormatException e) {
            log(String.format("%s%s", message, s));
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
