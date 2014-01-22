package com.ah3nong.wd.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Config {
	private static final Properties PROP = new Properties();
 
	private static final String FILE_SYSTEM_LOCATION = "conf.properties";
	private static final String CONSTANT_SYSTEM_LOCATION = "constants.properties";
	/**
	 * Initializes the config.
	 * 
	 * @param confFile
	 *            the config file path it will search for the file in the class
	 *            path if the path doesn't exist
	 * @exception InstantiationException
	 *                load properties happend error
	 */
	static {
		InputStream is = null;
		try {
			File file = new File(FILE_SYSTEM_LOCATION);
			if (file.exists()) {
				is = new FileInputStream(FILE_SYSTEM_LOCATION);
			} else {
				System.out.println("input conf file path not exists:"
						+ FILE_SYSTEM_LOCATION
						+ ", trying to search from class path.");
				is = Config.class.getClassLoader().getResourceAsStream(
						FILE_SYSTEM_LOCATION.substring(FILE_SYSTEM_LOCATION
								.lastIndexOf("/") + 1));

			}
			//加载CONF.RPOPERTIES文件
			PROP.load(is);

			file = new File(CONSTANT_SYSTEM_LOCATION);
			if (file.exists()) {
				is = new FileInputStream(CONSTANT_SYSTEM_LOCATION);
			} else {
				System.out.println("input constant file path not exists:"
						+ CONSTANT_SYSTEM_LOCATION
						+ ", trying to search from class path.");
				is = Config.class.getClassLoader().getResourceAsStream(
						CONSTANT_SYSTEM_LOCATION
								.substring(CONSTANT_SYSTEM_LOCATION
										.lastIndexOf("/") + 1));

			}
			//加载CONSTANT.RPOPERTIES文件
			PROP.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
		}
	}

	/**
	 * Read a string value
	 * 
	 * @param key
	 *            key from config file
	 * @return String value if exists
	 */
	public static String getString(final String key) {
		if (PROP == null) {
			throw new RuntimeException("Config hasn't been initialized yet.");
		}
		if (PROP.containsKey(key))
			try {
				return new String(PROP.getProperty(key).getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		else
			throw new RuntimeException("No config key found for [" + key + "]."
					+ " from PROP:" + PROP);
	}

	/**
	 * Read a int value
	 * 
	 * @param key
	 *            key from config file
	 * @return int value if exists
	 */
	public static int getInt(final String key) {
		if (PROP == null) {
			throw new RuntimeException("Config hasn't been initialized yet.");
		}
		if (PROP.containsKey(key))
			return Integer.parseInt(PROP.getProperty(key));
		else
			throw new RuntimeException("No config key found for [" + key + "].");
	}

	/**
	 * Read a int value
	 * 
	 * @param key
	 *            key from config file
	 * @param defaultValue
	 *            default value
	 * @return int value if exists
	 */
	public static int getInt(final String key, final int defaultValue) {
		if (PROP == null) {
			return defaultValue;
		}
		if (PROP.containsKey(key)) {
			try {
				return Integer.parseInt(PROP.getProperty(key));
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}

		return defaultValue;
	}

	/**
	 * Read a long value
	 * 
	 * @param key
	 *            key from config file
	 * @return long value if exists
	 */
	public static long getLong(final String key) {
		if (PROP == null) {
			throw new RuntimeException("Config hasn't been initialized yet.");
		}
		if (PROP.containsKey(key))
			return Long.parseLong(PROP.getProperty(key));
		else
			throw new RuntimeException("No config key found for [" + key + "].");
	}

	/**
	 * Read a boolean value
	 * 
	 * @param key
	 *            key from config file
	 * @return boolean true if value is 1 or true
	 */
	public static boolean getBoolean(final String key) {
		if (PROP == null) {
			throw new RuntimeException("Config hasn't been initialized yet.");
		}
		if (PROP.containsKey(key))
			if (PROP.getProperty(key).equals("true")
					|| PROP.getProperty(key).equals("1"))
				return true;
			else
				return false;
		else
			throw new RuntimeException("No config key found for [" + key + "].");
	}

	/**
	 * Read a byte value
	 * 
	 * @param key
	 *            key from config file
	 * @return byte value if exists
	 */
	public static byte getByte(final String key) {
		if (PROP == null) {
			throw new RuntimeException("Config hasn't been initialized yet.");
		}
		if (PROP.containsKey(key))
			return Byte.parseByte(PROP.getProperty(key));
		else
			throw new RuntimeException("No config key found for [" + key + "].");
	}
}
