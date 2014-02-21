package com.celerity.exception;

public class RateException extends RuntimeException{

	public RateException() {
		super();
	}

	public RateException(String message, Throwable cause, Integer productId) {
		super(message + ".  Occurred for product = " + productId, cause);
	}

	public RateException(String message, Integer productId) {
		super(message + ".  Occurred for product = " + productId);
	}
	
	

}
