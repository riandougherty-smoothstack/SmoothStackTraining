package com.ss.riandougherty.training.day_five.datetime;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public final class DateTimeResponseTest {
	
	@Test
	public void getPreviousThursdayTest() {
		assertEquals(DateTimeResponse.getPreviousThursday(LocalDate.of(2021, Month.APRIL.getValue(), 12)), LocalDate.of(2021, Month.APRIL.getValue(), 8));
		
		assertEquals(DateTimeResponse.getPreviousThursday(LocalDate.of(2021, Month.MARCH.getValue(), 11)), LocalDate.of(2021, Month.MARCH.getValue(), 4));
	}
	
	@Test
	public void getDaysInMonthsTest() {
		SortedMap<Month, Integer> desiredOutput;
		
		desiredOutput = new TreeMap<Month, Integer>();
		
		// Mapping from excelnotes.com
		desiredOutput.put(Month.JANUARY, 31);
		desiredOutput.put(Month.FEBRUARY, 28);
		desiredOutput.put(Month.MARCH, 31);
		desiredOutput.put(Month.APRIL, 30);
		desiredOutput.put(Month.MAY, 31);
		desiredOutput.put(Month.JUNE, 30);
		desiredOutput.put(Month.JULY, 31);
		desiredOutput.put(Month.AUGUST, 31);
		desiredOutput.put(Month.SEPTEMBER, 30);
		desiredOutput.put(Month.OCTOBER, 31);
		desiredOutput.put(Month.NOVEMBER, 30);
		desiredOutput.put(Month.DECEMBER, 31);
		
		assertEquals(DateTimeResponse.getDaysInMonths(Year.of(2021)), desiredOutput);
	}
	
	@Test
	public void getMondaysTest() {
		List<Integer> desiredOutput;
		
		desiredOutput = new ArrayList<>();
		
		desiredOutput.add(5);
		desiredOutput.add(12);
		desiredOutput.add(19);
		desiredOutput.add(26);
		
		assertEquals(DateTimeResponse.getMondays(YearMonth.of(2021, Month.APRIL.getValue())), desiredOutput);
		
		desiredOutput = new ArrayList<>();
		
		desiredOutput.add(1);
		desiredOutput.add(8);
		desiredOutput.add(15);
		desiredOutput.add(22);
		desiredOutput.add(29);
		
		assertEquals(DateTimeResponse.getMondays(YearMonth.of(2021, Month.MARCH.getValue())), desiredOutput);
	}
	
	@Test
	public void isFridayTheThirteenthTest() {
		assertTrue(DateTimeResponse.isFridayTheThirteenth(LocalDate.of(2021, Month.AUGUST.getValue(), 13)));
		
		assertFalse(DateTimeResponse.isFridayTheThirteenth(LocalDate.of(2021, Month.APRIL.getValue(), 13)));
	}
}
