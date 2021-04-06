package com.ss.riandougherty.training.day_one.one;

public final class Util {
	
	// non-instanciable
	private Util() {}
	
	/**
	 * Appends c n times to @StringBuilder sb.
	 * 
	 * @param sb - @StringBuilder destination
	 * @param c - character to append
	 * @param n - n times to append character
	 */
	public static void appendNChars(final StringBuilder sb, final char c, final int n) {
		if(n > 0) {
			int b;
			
			for(b = 0; b < n; b++) {
				sb.append(c);
			}
		} else {
			// addition -- since n is negative in this case
			sb.setLength(sb.length() + n);
		}
	}
	
	/**
	 * Returns a string of n chars.
	 * 
	 * @param c - character to duplicate
	 * @param n - times to duplicate character
	 * @return String of N characters
	 */
	public static String nCharString(final char c, final int n) {
		final StringBuilder sb = new StringBuilder();
		
		appendNChars(sb, c, n);
		
		return sb.toString();
	}
}
