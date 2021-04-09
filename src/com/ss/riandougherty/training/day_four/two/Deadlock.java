package com.ss.riandougherty.training.day_four.two;

public final class Deadlock implements Runnable {
	private final Object resourceA, resourceB;
	
	public Deadlock(final Object resourceA, final Object resourceB) {
		this.resourceA = resourceA;
		this.resourceB = resourceB;
	}

	@Override
	public void run() {
		synchronized(resourceA) {
			try {
				Thread.sleep(100L);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(resourceB) {
				// oops
				System.out.println("This code will probably never execute.");
			}
		}
	}
	
	public static void createDeadlockSituation() {
		final Object one, two;
		final Thread t1, t2;
		
		one = new Object();
		two = new Object();
		
		t1 = new Thread(new Deadlock(one, two));
		t2 = new Thread(new Deadlock(two, one));
		
		t1.start();
		t2.start();
	}
}
