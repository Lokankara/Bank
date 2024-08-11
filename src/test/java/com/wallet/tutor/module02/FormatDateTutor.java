package com.wallet.tutor.module02;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import static com.wallet.tutor.Logger.log;
import static org.junit.jupiter.api.Assertions.*;

import com.wallet.tutor.Logger;
import org.junit.jupiter.api.Test;

public class FormatDateTutor {
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    private final Formatter formatter = new Formatter();

    /**
     * Returns date in format dd.mm.yy
     * Use Formatter
     */
    public String getDateByFormatter(Date date) {

        String string = formatter.format(String.format("%td.%tm.%ty", date, date, date)).toString();

        log(string);

        return string;
    }

    /**
     * Returns date in format "27 of May, 2015"
     * Use Formatter
     */
    public String getDateString(Date date) {

        String string = formatter.format(String.format("%s", date)).toString();

        log(string);

        return string;
    }

    /**
     * Returns date in format "01.05.13"
     * Use SimpleDateFormat
     */
    public String getDateBySimpleDateFormat(Date date) {

        log(String.format("dateBySimpleDateFormat: %s", dateFormat.format(date)));

        return (dateFormat.format(date));

    }

    /**
     * Returns date of type Date, converted from the line in format dd.mm.yy
     * Use SimpleDateFormat, method parse()
     */
    public Date parseDDMMYY(String s) {
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFormatDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 4, 1); // 1st of May, 2013
        Date date = cal.getTime();

        String dateByFormatter = getDateByFormatter(date);
        log("dateByFormatter: " + dateByFormatter);
        assertEquals(dateByFormatter, "01.05.13");

        String dateBySimpleDateFormat = getDateBySimpleDateFormat(date);
        log("dateBySimpleDateFormat: " + dateBySimpleDateFormat);
        assertEquals(dateBySimpleDateFormat, "01.05.13");

        log(getDateString(new Date()));
    }

    @Test
    public void testParseDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 4, 1, 0, 0, 0); // 1st of May, 2013
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Date d = parseDDMMYY("01.05.13");
        assertEquals(date, d);
    }
}
