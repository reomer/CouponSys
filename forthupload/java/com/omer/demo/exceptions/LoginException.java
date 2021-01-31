package com.omer.demo.exceptions;


public class LoginException extends Exception{
	
	public LoginException(String message) {

		super("This Item already exists: " + message);
	}

}
