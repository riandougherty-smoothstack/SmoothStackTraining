package com.ss.riandougherty.training.day_five.lambdas.one;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class StringSort {
	private StringSort() {}
	
	// Util
	
	/**
	 * Custom utility function to sort by comparator given input and comparator reference.
	 */
	private static Collection<String> sortByComparator(final Collection<String> toSort, final Comparator<String> comparator) {
		return toSort.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}
	
	// Answers (in order)
	
	/**
	 * Sorts Strings by length.
	 */
	public static Collection<String> sortByLength(final Collection<String> toSort) {
		return sortByComparator(toSort, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
	}
	
	/**
	 * Sorts Strings by length reversed. 
	 */
	public static Collection<String> sortByLengthReversed(final Collection<String> toSort) {
		return sortByComparator(toSort, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
	}
	
	/**
	 * Sorts Strings alphabetically.
	 */
	public static Collection<String> sortAlphabetically(final Collection<String> toSort) {
		return sortByComparator(toSort, String::compareTo);
	}
	
	/**
	 * Sorts Strings alphabetically, but Strings containing 'e' are sorted first.
	 */
	public static Collection<String> sortByE(final Collection<String> toSort) {
		return sortByComparator(toSort, (s1, s2) -> {
			// custom comparator logic
			
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
	
	/**
	 * Sort by E, but in a different way.
	 */
	public static void sortByEOther(final String[] toSort) {
		Arrays.sort(toSort, (s1, s2) -> Utils.yourMethod(s1, s2));
	}
}
