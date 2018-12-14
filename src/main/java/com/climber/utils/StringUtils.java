package com.climber.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

	public static void main(String[] args) {
		String pwd = getMD5("123456");
		System.out.println(pwd);
	}

	public static boolean isEmpty(String value) {
		return (value == null || value.isEmpty());
	}

	public static String getMD5(String value) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}

		return result;
	}

}
