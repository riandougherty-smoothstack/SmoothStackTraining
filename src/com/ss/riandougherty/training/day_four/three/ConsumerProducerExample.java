package com.ss.riandougherty.training.day_four.three;

import java.util.concurrent.SynchronousQueue;

public abstract class ConsumerProducerExample implements Runnable {
	protected final SynchronousQueue<Integer> queue;
	
	public ConsumerProducerExample(final SynchronousQueue<Integer> queue) {
		this.queue = queue;
	}
	
	/**
	 *  helper function to print status of what thread is doing
	 */
	protected static void printStatus(final Integer item, final boolean isAdding) {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("Thread ");
		sb.append(Thread.currentThread().getId());
		sb.append(' ');
		
		final String opStr;
		if(isAdding) {
			opStr = "added";
		} else { // is removing
			opStr = "removed";
		}
		sb.append(opStr);
		sb.append(" item: ");
		sb.append(item);
		sb.append('.');
		
		System.out.println(sb.toString());
	}
	
	public static void main(final String[] args) {
		final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		final Thread consumerThread, producerThread;
		
		producerThread = new Thread(new Producer(queue));
		consumerThread = new Thread(new Consumer(queue));
		
		producerThread.start();
		consumerThread.start();
	}
}
