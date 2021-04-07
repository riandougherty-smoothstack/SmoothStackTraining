package com.ss.riandougherty.training.day_two.three;

public class Triangle implements Shape {
	private final double side1, side2, side3;
	
	public Triangle(final double side1, final double side2, final double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	@Override
	public double calculateArea() {
		final double tmp;
		
		tmp = (side1 + side2 + side3) / 2d;
		
		return Math.sqrt(tmp * ((tmp - side1) * (tmp - side2) * (tmp - side3)));
	}

	@Override
	public void display() {
		System.out.println("The area of this shape is: " + calculateArea() + ".");
		System.out.println("The dimensions of this shape are: [" + side1 + "," + side2 + "," + side3 + "].");
	}

}
