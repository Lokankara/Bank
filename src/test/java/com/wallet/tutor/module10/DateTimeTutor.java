package com.wallet.tutor.module10;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateTimeTutor {

    public static void main(String[] args) {

        // 1) print current time in London in format "HH:mm dd/MM/yyyy"
        ZonedDateTime londonZonedDateTime = ZonedDateTime.now(ZoneId.of("EST", ZoneId.SHORT_IDS)); //ZoneId.of("Europe/London")
        String timeLondon = londonZonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
        log.info(String.format("Current time in London %s", timeLondon));

        // 2) calculate your age in days, months and years
        LocalDateTime birthday = LocalDateTime.of(1982, Month.FEBRUARY, 25, 02, 22);
        LocalDateTime now = LocalDateTime.now();

        log.info(birthday.toString());
        log.info(now.toString());

        long days = ChronoUnit.DAYS.between(birthday, now);
        long months = ChronoUnit.MONTHS.between(birthday, now);
        long years = ChronoUnit.YEARS.between(birthday, now);

        log.info(String.format("I lived %s days", days));
        log.info(String.format("I lived %s months", months));
        log.info(String.format("I lived %s years", years));

        // 3) calculate and show the date of your next birthday in format dd.mm.yyyy

        LocalDateTime nextBirth = birthday.withYear(now.getYear());

        if (nextBirth.isBefore(now)) {
            nextBirth = nextBirth.plusYears(1);
        }

        String next = nextBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        log.info(String.format("The next birthday in %s", next));

        // 4) calculate your age in seconds and in hours
        long hours = ChronoUnit.HOURS.between(birthday, now);
        long seconds = ChronoUnit.SECONDS.between(birthday, now);

        log.info(String.format("I lived %s hours", hours));

        log.info(String.format("I lived %s seconds", seconds));

        // 5) show day of the week of the next birthday
        LocalDateTime nextBirthWeek = nextBirth.plusWeeks(1);

        log.info(String.format("week of the next birthday %s ", nextBirthWeek));

        // 6) check if your birthday this year is ahead or behind
        if (nextBirth.isBefore(now)) {
            log.info("ahead");
        } else {
            log.info("behind");
        }

        // 7) calculate time difference of local time and New York time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        log.info(String.format("Time in New York: %d:%d:%d%n", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));

        TimeZone timeZoneNewYork = TimeZone.getTimeZone("America/New_York");

        TimeZone timeZoneKiev = TimeZone.getTimeZone("Europe/Kiev");

        log.info(String.format("RawOffset Time in New York: %d", timeZoneNewYork.getRawOffset()));

        log.info(String.format("RawOffset Time in Kiev: %d", timeZoneKiev.getRawOffset()));

        int diff = timeZoneKiev.getRawOffset() - timeZoneNewYork.getRawOffset();

        log.info(String.format("diff: %d", TimeUnit.MILLISECONDS.toHours(diff)));

        String time = "02.04.2016 22:11";

        ZonedDateTime zonedDT = ZonedDateTime.parse("2016-04-02T22:11:00Z");

        ZonedDateTime zonedDateTime = zonedDT.withZoneSameInstant(timeZoneNewYork.toZoneId());

        log.info(zonedDateTime.toString());

        // use TimeZone.getRawOffset() to calculate difference in time in milliseconds
        // use TimeZone().getDefault() to get local TimeZone
        // use TimeUnit.MILLISECONDS.toHours() to convert it to hours

        // 8) let us have New York time "02.04.2016 22:11"; calculate local time and print it in the same format
        // use ZonedDateTime.withZoneSameInstant() method and ZoneOffset.systemDefault() to get local ZoneId
    }
}
