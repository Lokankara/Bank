package com.wallet.tutor.module10;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
@Slf4j
public class LocalDateDemo {

	public static void main(String[] args) throws InterruptedException {
		LocalDateTime timePoint = LocalDateTime.now();     // The current date and time
		LocalDate.of(2012, Month.DECEMBER, 12); // from values
		LocalDate.ofEpochDay(150);  // middle of 1970
		LocalTime.of(17, 18); // the train I took home today
		LocalTime.parse("10:15:30"); // From a String
		
		LocalDate theDate = timePoint.toLocalDate();
		Month month = timePoint.getMonth();
		int day = timePoint.getDayOfMonth();
		timePoint.getSecond();
		
		// Set the value, returning a new object
		LocalDateTime thePast = timePoint.withDayOfMonth(
		    10).withYear(2010);

		/* You can use direct manipulation methods, 
		    or pass a value and field pair */
		LocalDateTime yetAnother = thePast.plusWeeks(
		    3).plus(3, ChronoUnit.WEEKS);
		
		
		LocalDate today = LocalDate.now();
		LocalDate payday = today.with(
				TemporalAdjusters.lastDayOfMonth()).minusDays(2);

		LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
		LocalDate firstBirthday = dateOfBirth.plusYears(1);
		
		// Returns the current time based on your system clock and set to UTC. 
		Clock clock = Clock.systemUTC(); 
		log.info("Clock : " + LocalDateTime.now(clock)); 
		// Returns time based on system clock zone 
		Clock defaultClock = Clock.systemDefaultZone(); 
		log.info("Clock : " + LocalDateTime.now(defaultClock )); 
		
		ZonedDateTime zonedDatetimeFromZone1 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
		ZonedDateTime zonedDatetimeFromZone2 = ZonedDateTime.now(ZoneId.of("America/New_York"));
		ZonedDateTime zonedDatetimeFromZone3 = ZonedDateTime.now(ZoneId.of("Europe/Bucharest"));
		ZonedDateTime zonedDatetimeFromZone4 = ZonedDateTime.now();

		log.info(zonedDatetimeFromZone1.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
		log.info(zonedDatetimeFromZone2.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
		log.info(zonedDatetimeFromZone3.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
		log.info(zonedDatetimeFromZone4.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
	
	}
	
}
