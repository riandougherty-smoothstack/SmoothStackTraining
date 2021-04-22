package com.ss.riandougherty.eval.week_two.dao.service;

public class MenuReturn {
	public final Object returnValue;
	public final MenuItem newMenuItem;
	
	public MenuReturn(final Object returnValue, final MenuItem newMenuItem) {
		this.returnValue = returnValue;
		this.newMenuItem = newMenuItem;
	}
	
	public Object getReturnValue() {
		return this.returnValue;
	}
	
	public MenuItem getNewMenuItem() {
		return this.newMenuItem;
	}
}
