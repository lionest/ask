package com.ah3nong.wd.common;

import java.util.Hashtable;
import java.util.Map;

public class DataCache {
	private static final Map<Integer, Object> map = new Hashtable<Integer, Object>();

	public static Object get(Integer key) {
		return map.get(key);
	}

	public static void put(Integer key, Object value) {
		map.put(key, value);
	}
}
