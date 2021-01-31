package com.omer.exceptions;

public class AllreadyExistexception extends Exception {

	public AllreadyExistexception(String message) {

		super("This Item already exists: " + message);
	}
}
