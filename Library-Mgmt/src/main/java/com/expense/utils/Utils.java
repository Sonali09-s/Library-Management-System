package com.expense.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public LocalDateTime DateFormatter(String Date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		LocalDateTime issueDate = LocalDateTime.parse(Date, formatter);
		return issueDate;
		
	}
}
