package com.feitai.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Test {
	public static void main(String[] args) {
		String dateStr ="2012-02-29 23:32:55";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			System.out.println(sdf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
