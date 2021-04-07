package com.ss.riandougherty.training.day_two.two;

import java.util.concurrent.ThreadLocalRandom;

public final class TwoDimensionalArray {
	
	/**
	 * @param maxNum - upper bounds of random numbers to be inserted (inclusive)
	 * @param size1 - size of first dimension
	 * @param size2 - size of second dimension
	 * @return randomly generated array
	 */
	private static int[][] construct2DArray(final int maxNum, final int size1, final int size2) {
		if(size1 < 1 || size2 < 1) {
			throw new IllegalArgumentException("size value must be greater than 0.");
		}
		
		final int[][] result = new int[size1][size2];
		
		int a = 0;
		
		do {
			int b = 0;
			
			do {
				// add one since
				result[a][b] = ThreadLocalRandom.current().nextInt(maxNum + 1);
			} while(++b < size2);
		} while(++a < size1);
		
		return result;
	}
	
	public static void main(String[] args) {
		final int[][] arr;
		
		arr = construct2DArray(1000, 5, 5);
		
		int a, max, maxA, maxB;
		
		max = maxA = maxB = 0;
		
		a = 0;
		do {
			int b = 0;
			
			do {
				int num;
				
				num = arr[a][b];
				
				if(num > max) {
					max = num;
					
					maxA = a;
					maxB = b;
				}
			} while(++b < 5);
		} while(++a < 5);
		
		final StringBuilder sb = new StringBuilder();
		
		sb.append("Found max number \"");
		sb.append(max);
		sb.append("\" at [");
		sb.append(maxA);
		sb.append("][");
		sb.append(maxB);
		sb.append("].");
		
		System.out.println(sb.toString());
	}
}
