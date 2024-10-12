package com.wallet.tutor.module01;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SystemCurrentTimeTest {

    public static void main(String[] args) {
        SystemCurrentTimeTest systemClass = new SystemCurrentTimeTest();
        log.info(String.valueOf(systemClass.getTimeInMillis()));
    }

    /**
     * getTimeInMillis() Must return the current time in milliseconds
     */
    public long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    /**
     * The profiler should calculate how many milliseconds it took
     * Running the Runnable.run () method
     */
    public long profiler(Runnable run) {
        long start = System.currentTimeMillis();
        run.run();
        long end = System.currentTimeMillis();
        long delta = end - start;
        log.info(String.valueOf(delta));
        return delta;
    }

    /**
     * The method must return the date in milliseconds
     */
    public Date getDate(long timeInMillis) {
        return new Date(timeInMillis);
    }

    /**
     * The method must return the date to which the plusDays are added (or subtracted)
     */
    public Date getDatePlus(Date date, int plusDays) {
        long time = date.getTime() + (long) plusDays * 1000 * 60 * 60 * 24;
        return new Date(time);
    }

    @Test
    public void testGetDate() {
        Date date = getDate(1363877852603L);
        log.info(date.toString());
        assertEquals(date.getTime(), 1363877852603L);
        Date dateOfBeginning = getDate(0);
        log.info(dateOfBeginning.toString());
        assertEquals(dateOfBeginning.getTime(), 0);
    }

    @Test
    public void testGetDatePlus() {
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 3, 1, 12, 30, 0);
        cal.clear(Calendar.MILLISECOND);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2013, 3, 3, 12, 30, 0);
        cal2.clear(Calendar.MILLISECOND);
        Date datePlus = getDatePlus(cal.getTime(), 2);
        log.info(cal.getTime().toString());
        log.info(datePlus.toString());
        log.info(cal2.getTime().toString());
        log.info(datePlus.getTime() + ":" + cal2.getTimeInMillis());
    }

    @Test
    public void testGetTimeInMillis() {
        assertTrue(getTimeInMillis() > 1363877852603L);
    }

    @Test
    public void testForProfiler() {
        assertEquals(0, noOperationProfiler());
    }

    public long noOperationProfiler() {
        return profiler(() -> {
        });
    }

    public long forProfiler() {
        return profiler(() -> {
            for (int i = 0; i < 1000; i++) {
            }
        });
    }
}
