package com.ss.riandougherty.training.day_two.three;

public class Rectangle implements Shape {
	private final double side1, side2;
	
	
	public Rectangle(final double side1, final double side2) {
		this.side1 = side1;
		this.side2 = side2;
	}
	
	@Override
	public double calculateArea() {
		return side1 * side2;
	}

	@Override
	public void display() {
		System.out.println("The area of this shape is: " + calculateArea() + ".");
		System.out.println("The dimensions of this shape are: [" + side1 + "," + side2 + "].");
	}
}
