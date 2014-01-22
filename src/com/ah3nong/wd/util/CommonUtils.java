package com.ah3nong.wd.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Common Utilities
 * 
 */
public class CommonUtils {

	/**
	 * EncryptPassword by MD5
	 * 
	 * @param pass
	 *            the original password
	 * @return encrypted password
	 * @throws NoSuchAlgorithmException
	 *             no such algorithm
	 */
	public static String encryptPassword(String pass)
			throws NoSuchAlgorithmException {
		if (pass == null)
			return null;

		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte b[] = md.digest();
			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e);
		}
		return buf.toString();
	}
	
//	public static void main(String[] args){
//		try {
//			System.out.print(CommonUtils.encryptPassword("ah3nah3n"));
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * Returns values of all declared fields including public, protected,
	 * default (package) access, and private fields, but excludes inherited
	 * fields, of an Object as a Map.
	 * 
	 * @param source
	 *            The object to inspect
	 * @return Map
	 * @author John Tang
	 */
	public static final Map<String, Object> toMap(Object source) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		try {
			Field[] f = source.getClass().getDeclaredFields();
			for (int i = 0; i < f.length; ++i) {
				if (Modifier.isTransient(f[i].getModifiers())) {
					continue;
				}
				if (Modifier.isPrivate(f[i].getModifiers())) {
					f[i].setAccessible(true);
				}
				/* Added to allow printing for protected fields */
				if (Modifier.isProtected(f[i].getModifiers())) {
					f[i].setAccessible(true);
				}

				map.put(f[i].getName(), f[i].get(source));
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		return map;
	}

	public static void setDefaultValue(Object source) {
		Class<?> cls = source.getClass();
		Field[] f = source.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < f.length; ++i) {
				if (Modifier.isPrivate(f[i].getModifiers())
						&& "BigDecimal".equals(f[i].getType().getSimpleName())) {
					f[i].setAccessible(true);
					Method fieldSetMet = cls.getMethod(parSetName(f[i].getName()), f[i]
							.getType());
					fieldSetMet.invoke(source, BigDecimal.ZERO);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 拼接在某属性的 set方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	private static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

	/**
	 * 计算从1995年到今年的年份lsit
	 * 
	 * @return
	 */
	public static List<Integer> getLatestYear() {
		Calendar c = Calendar.getInstance();
		int last = c.get(Calendar.YEAR);
		int begin = 1995;
		List<Integer> yearList = new ArrayList<Integer>();
		for (int i = last; i >= begin; i--) {
			yearList.add(i);
		}
		return yearList;
	}
	
}