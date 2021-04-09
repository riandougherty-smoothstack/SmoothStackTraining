package com.ss.riandougherty.training.day_four.three;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Consumer extends ConsumerProducerExample {
	
	public Consumer(final SynchronousQueue<Integer> queue) {
		super(queue);
	}
	
	public Integer deQueue() throws InterruptedException {
		return queue.poll(10L, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		try {
			for(;;) {
				final Integer item = deQueue();
				
				// can be null if timeout elapses
				// will most likely be false, unless the system is under extreme load.
				if(item != null) {
					printStatus(item, false);
				}
			}
		} catch(final InterruptedException e) {}
	}
}
