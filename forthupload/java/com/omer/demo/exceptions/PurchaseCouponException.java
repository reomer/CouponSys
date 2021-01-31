package com.omer.demo.exceptions;

public class PurchaseCouponException extends Exception  {
	
	public PurchaseCouponException(String message) {

	super("This Purchase Action is not allowed: " + message);
}
}

