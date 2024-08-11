package com.wallet.tutor.module01;

import com.wallet.bank.utils.Logger;
import static java.lang.StrictMath.round;
import static java.lang.StrictMath.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MathTutor {

    /**
     * Returns a random age in the range from minAge to maxAge
     */
    public int getRandomAge(int minAge, int maxAge) {
        return (int) (Math.floor(Math.random() * (maxAge - minAge + 1)) + minAge);
    }

    /**
     * Pythagorean theorem:
     * Calculates the hypotenuse to the nearest
     * 2nd decimal place,
     * For example, if the cathetus is 2 and 3, then the hypotenuse = 3.61
     */
    public double getHypotenuse(double a, double b) {
        return round(sqrt(a * a + b * b) * 100) / 100.0;
    }

    @Test
    public void testGetHypotenuse() {
        double hypotenuse = getHypotenuse(2, 3);
        Logger.log(hypotenuse);
        assertEquals(3.61, hypotenuse, 0);
    }

    /**
     * Tests the fullness of the range MIN_AGE..MAX_AGE
     * And uniformity of distribution
     * (Each age should meet equally often)
     */
    @Test
    public void testRandomAge() {
        int ITERATIONS = 1000000;
        int MIN_AGE = 18;
        int MAX_AGE = 25;
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < ITERATIONS; i++) {
            int age = getRandomAge(MIN_AGE, MAX_AGE);
            Integer occur = occurrences.get(age);
            if (occur == null) occur = 0;
            occurrences.put(age, occur + 1);
        }
        Set<Integer> ages = occurrences.keySet();
        List<Integer> sortedAges = new ArrayList<Integer>(ages);
        Collections.sort(sortedAges);
        int min = 0, max = 0;
        double mean = ITERATIONS / (MAX_AGE - MIN_AGE + 1);
        double varianceSum = 0;
        for (Integer age : sortedAges) {
            int o = occurrences.get(age);
            min = Math.min(o, min);
            max = Math.max(o, max);
            int variance = (int) (o - mean);
            varianceSum += variance * variance;
            Logger.log("for age" + age + ":" + occurrences.get(age) + "occurrences, variance =" + variance);
        }
        double deviation = Math.sqrt(varianceSum / sortedAges.size());
        Logger.log("Standard deviation =" + deviation);
        double uniformity = (max - min) * 1d / ITERATIONS;
        Logger.log("Uniformity of age distribution: " + uniformity);
        assertEquals(MIN_AGE, sortedAges.get(0), 0);
        assertEquals(MAX_AGE, sortedAges.get(sortedAges.size() - 1), 0);
        assertTrue(uniformity < 0.2);
    }
}
