package com.ss.riandougherty.training.day_two.three;

public class Circle implements Shape {
	private final double radius;
	
	public Circle(final double radius) {
		this.radius = radius;
	}
	
	@Override
	public double calculateArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	@Override
	public void display() {
		System.out.println("The area of this shape is: " + calculateArea() + ".");
		System.out.println("The radius of this shape is: " + radius + ".");
	}
}
