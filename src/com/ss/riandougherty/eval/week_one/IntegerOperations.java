package com.ss.riandougherty.eval.week_one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			if(num == 2)
				return true;
			
			// if least-significant bit is set, then num is odd.
			if((num & 1) == 0)
				return false;
			
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
	
	private static void doAction(final int action, final int input) {
		String out = null;
		
		switch(action) {
		case 1: case 2:
			out = isOdd().check(input) ? "ODD" : "EVEN";
			
			break;
		case 3: case 4:
			out = isPrime().check(input) ? "PRIME" : "COMPOSITE";
			
			break;
		case 5:
			out = isPalindrome().check(input) ? "PALINDROME" : "NOT PALINDROME";
			
			break;
		default:
			System.err.println("Error: invalid operation.");
		}
		
		if(out != null)
			System.out.println(out);
	}
	
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			final String line = sc.next();
			final String[] line_split = line.split(" ");
			
			final int input;
			final int action;
			
			action = Integer.parseInt(line_split[0]);
			
			input = Integer.parseInt(line_split[1]);
			
			doAction(action, input);
		}
		
		sc.close();
	}
}
