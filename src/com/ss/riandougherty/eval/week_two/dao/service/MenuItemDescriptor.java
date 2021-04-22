package com.ss.riandougherty.eval.week_two.dao.service;

public class MenuItemDescriptor {
	private final String description;
	private final MenuItem menuItem;
	
	public MenuItemDescriptor(final String description, final MenuItem menuItem) {
		this.description = description;
		this.menuItem = menuItem;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public MenuItem getMenuItem() {
		return this.menuItem;
	}
}
