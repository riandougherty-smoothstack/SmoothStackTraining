package com.ss.riandougherty.eval.week_one;

import static org.junit.Assert.*;

import org.junit.Test;

public final class IntegerOperationsTest {
	
	@Test
	public void oddTest() {
		assertTrue(IntegerOperations.isOdd().check(1));
		assertTrue(IntegerOperations.isOdd().check(1));
	}
	
	@Test
	public void primeTest() {
		assertTrue(IntegerOperations.isPrime().check(2));
		assertTrue(IntegerOperations.isPrime().check(3));
		assertFalse(IntegerOperations.isPrime().check(4));
	}
	
	@Test
	public void palindromeTest() {
		assertTrue(IntegerOperations.isPalindrome().check(101));
		assertFalse(IntegerOperations.isPalindrome().check(102));
	}
}
