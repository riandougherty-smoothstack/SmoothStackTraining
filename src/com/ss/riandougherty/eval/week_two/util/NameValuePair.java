package com.ss.riandougherty.eval.week_two.util;

public class NameValuePair<T> {
	private final String name;
	private final T value;
	
	public NameValuePair(final String name, final T value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public T getValue() {
		return this.value;
	}
}
