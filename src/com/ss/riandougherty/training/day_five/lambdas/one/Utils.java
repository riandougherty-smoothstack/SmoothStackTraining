package com.ss.riandougherty.training.day_five.lambdas.one;

public final class Utils {
	private Utils() {}
	
	/**
	 * Used by sortByEOther
	 */
	public static int yourMethod(final String s1, final String s2) {
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
	}
}
