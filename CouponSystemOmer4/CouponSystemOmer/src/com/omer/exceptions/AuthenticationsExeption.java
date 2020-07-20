package com.omer.exceptions;

public class AuthenticationsExeption extends Exception {


		public AuthenticationsExeption() {
			super("The Email or Password provided doesnt exist");
		}
	}
