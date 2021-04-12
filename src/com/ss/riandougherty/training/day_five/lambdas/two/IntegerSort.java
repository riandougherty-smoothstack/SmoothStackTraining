package com.ss.riandougherty.training.day_five.lambdas.two;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class IntegerSort {
	private IntegerSort() {}
	
	/**
	 * Adds 'e' or 'o' prefix before element depending on whether element is even or odd.
	 */
	public List<String> tellEvenOdd(final Collection<Integer> input) {
		return input.stream().map(i -> {
			String r_str;
			
			if((i & 1) == 1) {
				r_str = "o";
			} else {
				r_str = "e";
			}
			
			r_str += i;
			
			return r_str;
		}).collect(Collectors.toList());
	}
}
