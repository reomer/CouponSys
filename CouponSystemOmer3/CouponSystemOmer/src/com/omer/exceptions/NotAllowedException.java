package com.omer.exceptions;

public class NotAllowedException extends Exception {

		public NotAllowedException(String s) {
			super("This action is not allowed-  "+s);
		}
	}

