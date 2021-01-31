package com.omer.demo.exceptions;

public class NotAllowedException extends Exception {

		public NotAllowedException(String message) {

			super("This Action is not allowed: " + message);
		}
	}

