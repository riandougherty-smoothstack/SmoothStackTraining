package com.ss.riandougherty.eval.week_one;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ListOperations {
	private ListOperations() {}
	
	/**
	 * Gets least-significant digit (rightmost digit).
	 */
	public static List<Integer> toRightMost(final Collection<Integer> input) {
		return input.stream()
				// getting rightmost digit is same as calculating remainder of division by 10.
				.map(num -> num % 10).
				collect(Collectors.toList());
	}
	
	/**
	 * Multiplies each element in list by 2.
	 */
	public static List<Integer> multByTwo(final Collection<Integer> input) {
		return input.stream()
				// left bitshift by 1 is the same as multiplication by 2, except much faster.
				.map(num -> num << 1)
				.collect(Collectors.toList());
	}
	
	/**
	 * Removes all cases of 'x' in input list. 
	 */
	public static List<String> removeX(final Collection<String> input) {
		return input.stream()
				.map(str -> str.replaceAll("x", ""))
				.collect(Collectors.toList());
	}
	
	/**
	 * Returns true if input list can sum to 10 from elements in sequential order.
	 */
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
