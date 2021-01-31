package com.omer.demo.utils;

public class PrintStringUtils {
	public static void printedException(String getMessage, String testDescription) {
		String STARS = "***";
		System.out.println(STARS + " Initiated Exception - " + testDescription + " but " + getMessage + " " + STARS);
	}

}
