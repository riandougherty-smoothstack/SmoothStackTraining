package com.ss.riandougherty.training.day_two.one;

/**
 * Main takes integer arguments from command line and sums them.
 */
public final class CLAddAll {
	
	private CLAddAll() {}
	
	public static void main(final String[] args) {
		int i, sum;
		
		sum = 0;
		
		for(i = 0; i < args.length; i++) {
			try {
				sum += Integer.parseInt(args[i]);
			} catch(final NumberFormatException e) {
				System.err.println("Error at argument #" + (i + 1) + ": Input not a number! Ignoring value...");
			}
		}
		
		System.out.println("Sum: " + sum + ".");
	}
}
