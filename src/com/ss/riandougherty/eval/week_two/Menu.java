package com.ss.riandougherty.eval.week_two;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Menu implements MenuAction {
	private final List<MenuOption> options;
	private MenuAction previousMenu;
	private String title;
	
	public Menu(final List<MenuOption> options, final MenuAction previousMenu, final String title) {
		this.options = options;
		this.previousMenu = previousMenu;
		this.title = title;
	}
	
	public Menu(final List<MenuOption> options, final MenuAction previousMenu) {
		this(options, previousMenu, null);
	}
	
	public void select() {
		if(title != null) {
			System.out.println(title);
		}
		
		List<MenuOption> localOptions = new ArrayList<>(options);
		
		if(previousMenu != null) {
			localOptions.add(new MenuOption("(Return to previous)", previousMenu));
		}
		
		int i;
		
		for(i = 1; i <= localOptions.size(); i++) {
			System.out.println(i + ") " + localOptions.get(i - 1).toString());
		}
		
		System.out.flush();
		System.err.flush();
		
		@SuppressWarnings("resource")
		final Scanner sc = new Scanner(System.in);
		
		int selectedOption = -1;
		
		while(sc.hasNext()) {
			try {
				selectedOption = sc.nextInt();
				
				if(selectedOption < 1 || selectedOption > localOptions.size()) {
					throw new NoSuchElementException();
				}
				
				break;
			} catch(final NoSuchElementException e) {
				System.err.println("Error: You must input an integer index.");
			}
		}
		
		localOptions.get(selectedOption - 1).getAction().select();
	}
	
	public List<MenuOption> getOptions() {
		return this.options;
	}
	
	public MenuAction getPreviousMenu() {
		return this.previousMenu;
	}
	
	public void setPreviousMenu(final MenuAction previousMenu) {
		this.previousMenu = previousMenu;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(final String title) {
		this.title = title;
	}
}
