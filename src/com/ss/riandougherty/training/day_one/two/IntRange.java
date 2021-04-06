package com.ss.riandougherty.training.day_one.two;

public final class IntRange {
	private final int lowerBound, upperBound;
	
	public IntRange(final int lowerBound, final int upperBound) {
		if(lowerBound > upperBound) {
			throw new IllegalArgumentException("lowerBound cannot be greater than upperBound");
		}
		
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public int getLowerBound() {
		return lowerBound;
	}
	
	public int getUpperBound() {
		return upperBound;
	}
	
	public boolean isInRange(final int test) {
		return lowerBound <= test && test <= upperBound;
	}
}
