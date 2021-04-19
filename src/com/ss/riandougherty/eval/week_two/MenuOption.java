package com.ss.riandougherty.eval.week_two;

public class MenuOption {
	public final String option;
	public final MenuAction action;
	
	public MenuOption(final String option, final MenuAction action) {
		this.option = option;
		this.action = action;
	}
	
	@Override
	public String toString() {
		return option;
	}
	
	public MenuAction getAction() {
		return action;
	}
}
