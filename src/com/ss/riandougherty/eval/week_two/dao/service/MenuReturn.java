package com.ss.riandougherty.eval.week_two.dao.service;

public class MenuReturn {
	public Object returnValue;
	public MenuItem newMenuItem;
	
	public MenuReturn(final Object returnValue, final MenuItem newMenuItem) {
		this.returnValue = returnValue;
		this.newMenuItem = newMenuItem;
	}
	
	public MenuReturn() {
		this(null, null);
	}
	
	public Object getReturnValue() {
		return this.returnValue;
	}
	
	public void setReturnValue(final Object value) {
		this.returnValue = value;
	}
	
	public MenuItem getNewMenuItem() {
		return this.newMenuItem;
	}
	
	public void setMenuItem(final MenuItem item) {
		this.newMenuItem = item;
	}
}
