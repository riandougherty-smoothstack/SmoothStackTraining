package com.ss.riandougherty.eval.week_one;

public final class IntegerOperations {
	private IntegerOperations() {}
	
	/**
	 * Returns PerformOperation that tests if number is odd.
	 * 
	 * i.e. it is not divisible by 2.
	 */
	public static PerformOperation isOdd() {
		return ((num) -> {
			// if least-significant bit is set, then num is odd. 
			return ((num & 1) == 1);
		});
	}
	
	/**
	 * Returns PerformOperation that tests if number is prime.
	 * 
	 * i.e. number is only divisible by one and itself.
	 */
	public static PerformOperation isPrime() {
		return ((num) -> {
			// if least-significant bit is set, then num is odd. 
			if((num & 1) == 0) {
				return false;
			}
			
			int a;
			
			// only have to check up to sqrt(num)
			// no need to test even numbers
			for(a = 3; a < Math.sqrt(num); a += 2) {
				if(num % a == 0)
					return false;
			}
			
			return true;
		});
	}
	
	/**
	 * Returns PerformOperation that tests if number is a palindrome.
	 * 
	 * i.e. if number in base-10 representation is flipped, it equals itself.
	 */
	public static PerformOperation isPalindrome() {
		return ((num) -> {
			// palindrome is base-10 specific, so just convert to string
			// since it converts it to base-10 representation.
			final String num_str;
			final char[] num_str_chars;
			final int len;
			
			num_str = String.valueOf(num);
			len = num_str.length();
			
			if(len == 1) {
				return true;
			}
			
			num_str_chars = num_str.toCharArray();
			
			int a;
			
			// iterates over each position from the outside in
			for(a = 0; a < (len >> 1); a++) {
				if(num_str_chars[a] != num_str_chars[(len - 1) - a]) {
					return false;
				}
			}
			
			return true;
		});
	}
}
