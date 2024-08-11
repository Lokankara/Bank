package com.wallet.tutor.module01;

import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class OverflowTest {

    /**
     * Multiply 2 numbers, excite ArithmeticException in case of overflow
     */
    public Integer multiply(int a, int b) {
        try {
            return Math.multiplyExact(a, b);
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Integer overflow: " + e.getMessage());
        }
    }

    @Test
    public void testOverflow() {
        int i1 = 34524235;
        int i2 = 23423423;

        assertThrows(ArithmeticException.class, () -> {
            this.multiply(i1, i2);
        });
    }

    @Test
    public void testSum() {
        double d = Double.POSITIVE_INFINITY * 10;
        log.info("doubleMultiply=" + d);
    }
}
