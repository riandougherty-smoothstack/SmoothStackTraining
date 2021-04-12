package com.ss.riandougherty.eval.week_one;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ListOperations {
	private ListOperations() {}
	
	public static List<Integer> toRightMost(final Collection<Integer> input) {
		return input.stream()
				.map(num -> num % 10).
				collect(Collectors.toList());
	}
	
	public static List<Integer> multByTwo(final Collection<Integer> input) {
		return input.stream()
				.map(num -> num << 1)
				.collect(Collectors.toList());
	}
	
	public static List<String> removeX(final Collection<String> input) {
		return input.stream()
				.map(str -> str.replaceAll("x", ""))
				.collect(Collectors.toList());
	}
	
	public static boolean canSumSequentially(final List<Integer> input) {
		int firstNumIndex, sum, a;
		
		firstNumIndex = sum = 0;
		
		for(a = 0; a < input.size(); a++) {
			sum += input.get(a);
			
			while(sum > 10) {
				// pop off first num
				sum -= input.get(firstNumIndex);
				
				// advance first num
				firstNumIndex++;
			}
			
			if(sum == 10) {
				return true;
			}
		}
		
		return false;
	}
}
