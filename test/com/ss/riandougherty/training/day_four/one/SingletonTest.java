package com.ss.riandougherty.training.day_four.one;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public final class SingletonTest {
	
	@Test
	public void getInstance() {
		assertNotNull(Singleton.getInstance());
	}
}
