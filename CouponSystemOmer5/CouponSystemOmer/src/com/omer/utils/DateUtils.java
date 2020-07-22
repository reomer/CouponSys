package com.omer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static java.sql.Date convertUtilDateToSQL(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.util.Date setDate(int DD, int MM, int YYYY) {

		Calendar cal = Calendar.getInstance();
		cal.set(DD, MM-1, YYYY);
		
		Date date = cal.getTime();
		return date;
	}
	public static java.util.Date calcDate(int DD, int MM, int YYYY) {

		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate localDate = LocalDate.of(YYYY, MM, DD);

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
	}
}
