package com.climber.utils;

public class ToolUtils {
	public static String appendAnd(String value) {
		if (!StringUtils.isEmpty(value))
			return " and ";
		else
			return "";
	}
}
