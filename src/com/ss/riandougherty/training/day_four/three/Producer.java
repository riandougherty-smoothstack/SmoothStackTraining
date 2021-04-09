package com.ss.riandougherty.training.day_four.three;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends ConsumerProducerExample {
	
	public Producer(final SynchronousQueue<Integer> queue) {
		super(queue);
	}
	
	public Integer addQueue() throws InterruptedException {
		final Integer num = ThreadLocalRandom.current().nextInt();
		
		queue.offer(num);
		
		return num;
	}
	
	@Override
	public void run() {
		try {
			for(;;) {
				try {
					final Integer item = addQueue();
					
					printStatus(item, true);
				} catch(final IllegalStateException e) {
					// wait again if queue is full. (finally block)
				} finally {
					// every 10ms it adds an item.
					Thread.sleep(10L);
				}
			}
		} catch(final InterruptedException e) {}
	}
}
