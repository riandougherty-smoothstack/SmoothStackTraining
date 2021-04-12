package com.ss.riandougherty.training.day_five.lambdas.one;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class StringSort {
	private StringSort() {}
	
	// Util
	
	private static Collection<String> sortByComparator(final Collection<String> toSort, final Comparator<String> comparator) {
		return toSort.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}
	
	// Answers (in order)
	
	public static Collection<String> sortByLength(final Collection<String> toSort) {
		return sortByComparator(toSort, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
	}
	
	public static Collection<String> sortByLengthReversed(final Collection<String> toSort) {
		return sortByComparator(toSort, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
	}
	
	public static Collection<String> sortAlphabetically(final Collection<String> toSort) {
		return sortByComparator(toSort, String::compareTo);
	}
	
	public static Collection<String> sortByE(final Collection<String> toSort) {
		return sortByComparator(toSort, (s1, s2) -> {
			final boolean s1_e, s2_e;
			
			s1_e = s1.contains("e");
			s2_e = s2.contains("e");
			
			if(s1_e) {
				if(s2_e) {
					return s1.compareTo(s2);
				}
				
				return -1;
			}
			
			if(s2_e) {
				return 1;
			}
			
			return s1.compareTo(s2);
		});
	}
	
	public static void sortByEOther(final String[] toSort) {
		Arrays.sort(toSort, (s1, s2) -> Utils.yourMethod(s1, s2));
	}
}
