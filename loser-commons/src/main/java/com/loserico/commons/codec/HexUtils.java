package com.loserico.commons.codec;

/**
 * 十六进制操作相关API
 * <p>
 * Copyright: Copyright (c) 2019-08-11 11:45
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 * @on
 */
public final class HexUtils {

	/**
	 * Hex十六进制字符串转成ASCII字符串
	 * 
	 * Hex如果是有空格隔开的, 那么中间的所有空格都会被去掉
	 * 
	 * @param hex
	 * @return String
	 */
	public static String hexToString(String hex) {
		if (hex == null || "".equals(hex.trim())) {
			return null;
		}
		hex = hex.replaceAll("\\s", "");
		
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < hex.length(); i += 2) {
			// grab the hex in pairs
			String str = hex.substring(i, i + 2);
			// convert hex to decimal
			int decimal = Integer.parseInt(str, 16);
			// convert the decimal to character
			output.append((char) decimal);
		}
		return output.toString();
	}

	/**
	 * ASCII字符串转成16进制字符串形式
	 * 
	 * @param str
	 * @return 十六进制字符串
	 */
	public static String stringToHex(String str) {

		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}
	
	/**
	 * 十六进制转成Integer
	 * @param hex
	 * @return Integer
	 */
	public static Integer hexToInteger(String hex) {
		if (hex == null || "".equals(hex.trim())) {
			return null;
		}
		
		if (hex.toLowerCase().indexOf("0x") != -1) {
			hex = hex.substring(2);
		}
		
		return Integer.parseInt(hex, 16);
	}
	
	public static Long hexToLong(String hex) {
		if (hex == null || "".equals(hex.trim())) {
			return null;
		}
		
		if (hex.toLowerCase().indexOf("0x") != -1) {
			hex = hex.substring(2);
		}
		
		return Long.parseLong(hex, 16);
	}
	
	public static String integerToHex(Integer i) {
		if (i == null) {
			return null;
		}
		
		return Integer.toHexString(i);
	}
}
