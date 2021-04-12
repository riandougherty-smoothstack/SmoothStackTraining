package com.ss.riandougherty.eval.week_one;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public final class ListOperationsTest {
	
	@Test
	public void rightDigitTest() {
		final List<Integer> input, desiredOutput;
		
		input = new ArrayList<>();
		desiredOutput = new ArrayList<>();
		
		input.add(55);
		desiredOutput.add(5);
		
		input.add(69);
		desiredOutput.add(9);
		
		assertEquals(ListOperations.rightDigit(input), desiredOutput);
	}
	
	@Test
	public void doublingTest() {
		List<Integer> input, desiredOutput;
		
		input = new ArrayList<>();
		desiredOutput = new ArrayList<>();
		
		input.add(10);
		desiredOutput.add(20);
		
		input.add(22);
		desiredOutput.add(44);
		
		assertEquals(ListOperations.doubling(input), desiredOutput);
	}
	
	@Test
	public void noXTest() {
		List<String> input, desiredOutput;
		
		input = new ArrayList<>();
		desiredOutput = new ArrayList<>();
		
		input.add("this string should not contain this x");
		desiredOutput.add("this string should not contain this ");
		
		assertEquals(ListOperations.noX(input), desiredOutput);
	}
	
	@Test
	public void groupSumClumpTest() {
		List<Integer> input;
		
		input = new ArrayList<>();
		
		input.add(8);
		input.add(1);
		input.add(2);
		input.add(7);
		input.add(69);
		
		assertTrue(ListOperations.groupSumClump(input));
		
		// empty
		input = new ArrayList<>();
		
		assertFalse(ListOperations.groupSumClump(input));
		
		input.add(5);
		input.add(7);
		input.add(2);
		input.add(5);
		input.add(4);
		
		assertFalse(ListOperations.groupSumClump(input));
	}
}
