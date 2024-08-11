package com.wallet.tutor.module01;

import static com.wallet.tutor.Logger.log;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SystemCurrentTimeTutor {

    public static void main(String[] args) {
        SystemCurrentTimeTutor systemClass = new SystemCurrentTimeTutor();
        log(systemClass.getTimeInMillis());
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
     *
     * @param run
     * @return
     */
    public long profiler(Runnable run) {
        long start = System.currentTimeMillis();
        run.run();
        long end = System.currentTimeMillis();
        long delta = end - start;
        log(delta);
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
        log(date.toString());
        assertEquals(date.getTime(), 1363877852603L);
        Date dateOfBeginning = getDate(0);
        log(dateOfBeginning.toString());
        assertEquals(dateOfBeginning.getTime(), 0);
    }

    @Test
    public void testGetDatePlus() {
        // create date for 1.04.2013, 12:30
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 3, 1, 12, 30, 0);
        cal.clear(Calendar.MILLISECOND);
        // create date for 3.04.2013, 12:30
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2013, 3, 3, 12, 30, 0);
        cal2.clear(Calendar.MILLISECOND);
        Date datePlus = getDatePlus(cal.getTime(), 2);
        log(cal.getTime().toString());
        log(datePlus.toString());
        log(cal2.getTime().toString());
        log(datePlus.getTime() + ":" + cal2.getTimeInMillis());
        assertEquals("datePlus() Returns an incorrect date", cal2.getTime());
    }

    @Test
    public void testGetTimeInMillis() {
        assertTrue(getTimeInMillis() > 1363877852603L);
    }

    @Test
    public void testForProfiler() {
        assertEquals(0, noOperationProfiler());
        assertTrue(forProfiler() > 0);
    }

    public long noOperationProfiler() {
        return profiler(new Runnable() {
            public void run() {
            }
        });
    }

    public long forProfiler() {
        return profiler(() -> {
            for (int i = 0; i < 100000000; i++) ;
        });
    }

}
