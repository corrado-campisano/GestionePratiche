package eu.servicemix.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDatetimeTest {

	public static void main(String[] args) {
		String str = "1986-04-08 12:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		System.out.println(dateTime.getDayOfMonth());
	}

}
