package com.loserico.cache.utils;

public final class StringUtils {

	public static boolean isNotBlank(String s) {
		return s != null && !"".equals(s.trim());
	}

}
