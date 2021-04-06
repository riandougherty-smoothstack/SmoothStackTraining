package com.ss.riandougherty.training.day_one.one;

public final class PatternProgram {
	private static final TextFormatter FORMATTER;
	
	static {
		FORMATTER = new TextFormatter(11, null);
	}
	
	// non-instanciable
	private PatternProgram() {}
	
	public static void printOne() {
		FORMATTER.setAlignment(Alignment.LEFT);
		
		System.out.println("1)");
		FORMATTER.printCharInc('*', 1, 1, 4);
		FORMATTER.printNCharString('.', 9);
	}
	
	public static void printTwo() {
		FORMATTER.setAlignment(Alignment.LEFT);
		
		System.out.println("2)");
		FORMATTER.printNCharString('.', 10);
		FORMATTER.printCharInc('*', 4, -1, 4);
	}
	
	public static void printThree() {
		FORMATTER.setAlignment(Alignment.CENTER);
		
		System.out.println("3)");
		FORMATTER.printCharInc('*', 1, 2, 4);
		FORMATTER.printNCharString('.', 11);
	}
	
	public static void printFour() {
		FORMATTER.setAlignment(Alignment.CENTER);
		
		System.out.println("4)");
		
		FORMATTER.printNCharString('.', 11);
		FORMATTER.printCharInc('*', 7, -2, 4);
	}
	
	public static void main(final String[] args) {
		printOne();
		printTwo();
		printThree();
		printFour();
	}
}
