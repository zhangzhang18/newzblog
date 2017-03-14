package com.zblog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Comments :
 * @Project :
 * @author wangyp2
 * @date 2012-2-2
 * @version 1.0
 */
public class StringUtil {

	/**
	 * @author wangyp2
	 * @date 2012-2-2
	 */
	public static String returnNull(String string) {
		if (string == null || string.equals(null)) {
			return "null";
		} else {
			return string;
		}
	}

	public static String cut86(String string) {
		if (string.indexOf("+86") >= 0) {
			return string.replace("+86", "");
		} else {
			return string;
		}
	}

	public static String cutHyphen(String string) {
		if (string.indexOf("-") >= 0) {
			return string.replace("-", "");
		} else {
			return string;
		}
	}

	public static boolean isEmpty(String string) {
		if (string == null || string.length() == 0) {
			return true;
		}
		return false;
	}

	public static String[] cutKeyValue(String string) {
		String[] strings = null;
		if (string.indexOf("=") >= 0) {
			strings = string.split("=");
		}
		return strings;
	}

	public static String cutValue(String string) {
		String valueString = null;
		if (string.indexOf("=") >= 0) {
			valueString = string.split("=")[1];
		}
		return valueString;
	}

	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern
		        .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	/**
	 * main函数.
	 * 
	 * @param args
	 *            启动参数
	 * @throws Exception
	 *             Exception
	 */
	public static void main(String... args) throws Exception {
		System.out.println(checkEmail("1@aaaa.com.cn"));
	}
}
