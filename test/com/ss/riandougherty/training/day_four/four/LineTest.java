package com.ss.riandougherty.training.day_four.four;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class LineTest {
	private static final double delta = 0.0001d;
	
	private final Line line = new Line(5, 7, 2, 3);
	
	@Test
	public void getSlope() {
		assertEquals(4d / 3d, line.getSlope(), delta);
	}
	
	@Test
	public void getDistance() {
		assertEquals(5.0d, line.getDistance(), delta);
	}
	
	@Test
	public void parallelTo() {
		assertTrue(line.parallelTo(new Line(5, 8, 2, 4)));
	}
}
