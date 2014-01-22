package com.ah3nong.wd.util;

/**
 * some useful common methods
 * 
 */
public class StringHelper {

	/**
	 * encode the html code
	 * 
	 * @param value
	 *            target String
	 * @return String result String
	 */
	public static String encodeHTML(String value) {
		if (value == null || value.length() == 0) {
			return value;
		}
		StringBuffer result = null;
		String filtered = null;
		for (int i = 0; i < value.length(); i++) {
			filtered = null;
			switch (value.charAt(i)) {
			case '<':
				filtered = "&lt;";
				break;
			case '>':
				filtered = "&gt;";
				break;
			case '&':
				filtered = "&amp;";
				break;
			case '"':
				filtered = "&quot;";
				break;
			case '\'':
				filtered = "&#39;";
				break;
			case '\\':
				filtered = "\\\\";
			}
			if (result == null) {
				if (filtered != null) {
					result = new StringBuffer(value.length() + 50);
					if (i > 0) {
						result.append(value.substring(0, i));
					}
					result.append(filtered);
				}
			} else {
				if (filtered == null) {
					result.append(value.charAt(i));
				} else {
					result.append(filtered);
				}
			}
		}
		return result == null ? value : result.toString();
	}

	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "...";
		}
		return str;
	}

	/**
	 * 根据给的NODE PATH来计算下一个NODE PATH
	 * 
	 * @param nodePath
	 */
	public static String findMaxNextValue(String nodePath) {
		if (nodePath == null) {
			return null;
		}
		String lastNumber = nodePath.trim().substring(nodePath.length() - 2);
		String firstNumbers = nodePath.trim().substring(0,nodePath.length() - 2);
		int nextValue = 0;
		if (lastNumber.startsWith("0")) {
			String s = lastNumber.substring(lastNumber.length() - 1);
			if (Validity.isNumber(s)) {
				nextValue = Integer.parseInt(s);
				if (nextValue == 9) {
					return firstNumbers+"10";
				} else {
					return firstNumbers+"0" + (nextValue + 1);
				}
			}
		} else {
			if (Validity.isNumber(lastNumber)) {
				nextValue = Integer.parseInt(lastNumber);
				return firstNumbers+(nextValue + 1) + "";
			}
		}
		return null;
	}

}
