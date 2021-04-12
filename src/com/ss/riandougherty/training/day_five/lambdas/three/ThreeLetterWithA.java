package com.ss.riandougherty.training.day_five.lambdas.three;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ThreeLetterWithA {
	private ThreeLetterWithA() {}
	
	public List<String> getThreeLetterWithAs(final Collection<String> input) {
		return input.stream()
				.filter(str -> str.length() == 3)
				.filter(str -> str.startsWith("a"))
				.collect(Collectors.toList());
	}
}
