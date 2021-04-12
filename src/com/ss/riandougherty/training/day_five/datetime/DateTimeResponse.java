package com.ss.riandougherty.training.day_five.datetime;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public final class DateTimeResponse {
	private DateTimeResponse() {}
	
	/*
	 * Q1: Which class would you use to store your birthday in
	 * years, months, days, seconds, and nanoseconds?
	 * 
	 * A: LocalDateTime
	 */
	
	/*
	 * Q2: Given a random date, how would you find the date of the previous Thursday?
	 * 
	 * A: |
	 *    V
	 */
	
	public static LocalDate getPreviousThursday(final LocalDate from) {
		final int daysAgo;
		
		daysAgo = (DayOfWeek.values().length - DayOfWeek.THURSDAY.getValue()) + from.getDayOfWeek().getValue();
		
		return from.minusDays(daysAgo);
	}
	
	/*
	 * Q3: What is the difference between a ZoneId and a ZoneOffset?
	 * 
	 * A: ZoneId is a unique identifier to internally map an identifier to a timezone.
	 * ZoneOffset is the difference between a given timezone and UTC time.
	 */
	
	/*
	 * Q4_1: How would you convert an Instant to a ZonedDateTime?
	 * 
	 * Q4_2: How would you convert a ZonedDateTime to an Instant?
	 * 
	 * A: |
	 *    V
	 */
	
	public static Instant instantFromZonedDateTime(final ZonedDateTime zdt) {
		return zdt.toInstant();
	}
	
	public static ZonedDateTime zonedDateTimeFromInstant(final Instant i, final ZoneId zone) {
		return ZonedDateTime.ofInstant(i, zone);
	}
	
	/*
	 * Q5: Write an example that, for a given year, reports the length of each month within that year.
	 * 
	 * A: |
	 *    V
	 */
	
	public static SortedMap<Month, Integer> getDaysInMonths(final Year year) {
		final SortedMap<Month, Integer> result;
		final int monthsInYear;
		final Month[] months;
		
		result = new TreeMap<Month, Integer>();
		months = Month.values();
		monthsInYear = months.length;
		
		int a;
		for(a = 1; a <= monthsInYear; a++) {
			final Month month = months[a-1];
			
			result.put(month, year.atMonth(month).lengthOfMonth());
		}
		
		return result;
	}
	
	public static void questionFive() {
		getDaysInMonths(Year.now()).forEach((month, length) -> {
			System.out.println(month.toString() + ": " + length);
		});
	}
	
	/*
	 * Q6: Write an example that, for a given month of the current year, lists all of the Mondays in that month.
	 * 
	 * A: |
	 *    V
	 */
	
	public static List<Integer> getMondays(final YearMonth ym) {
		final List<Integer> result;
		final int nextMonday;
		
		result = new ArrayList<>();
		nextMonday = (DayOfWeek.values().length - ym.atDay(1).getDayOfWeek().getValue()) + DayOfWeek.MONDAY.getValue() + 1;

		
		int a;
		
		if(ym.atDay(1).getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			a = 1;
		} else {
			a = nextMonday;
		}
		
		do {
			result.add(a);
		} while((a += 7) <= ym.lengthOfMonth());
		
		return result;
	}
	
	/*
	 * Q7: Write an example that tests whether a given date occurs on Friday the 13th.
	 * 
	 * A: |
	 *    V
	 */
	
	public static boolean isFridayTheThirteenth(final LocalDate ld) {
		if(ld.getDayOfMonth() == 13 && ld.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		getMondays(YearMonth.now()).forEach(System.out::println);;
	}
}
