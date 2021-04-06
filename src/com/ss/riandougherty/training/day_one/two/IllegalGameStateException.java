package com.ss.riandougherty.training.day_one.two;

public class IllegalGameStateException extends Exception {
	private static final long serialVersionUID = 559591221924823781L;
	
	public IllegalGameStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalGameStateException(String message) {
		super(message);
	}

	public IllegalGameStateException(Throwable cause) {
		super(cause);
	}
}
