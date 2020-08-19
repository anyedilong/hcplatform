package com.java.moudle.common.utils.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取Properties文件 File: bltwsProperties.java
 */
public final class PropertiesUtil {

	protected static Properties dictProp;
	protected static Properties followProp;
	protected static Properties regionProp;
	protected static Properties registerProp;

	static {
		dictProp = new Properties();
		followProp = new Properties();
		regionProp = new Properties();
		registerProp = new Properties();
		try {
			dictProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dict.properties"));
			followProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("followUrl.properties"));
			regionProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("regionUrl.properties"));
			registerProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("register.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getFollow(String key) {
		Object obj = followProp.get(key);
		if (null == obj)
			return "";
		return obj.toString();
	}
	
	public static String getRegion(String key) {
		Object obj = regionProp.get(key);
		if (null == obj)
			return "";
		return obj.toString();
	}
	
	public static String getRegister(String key) {
		Object obj = registerProp.get(key);
		if (null == obj)
			return "";
		return obj.toString();
	}

	public static String getDict(String key) {
		Object obj = dictProp.get(key);
		if (null == obj)
			return "";
		return obj.toString();
	}
	
	/**
	 * 私有构造方法，不需要创建对象
	 */
	private PropertiesUtil() {

	}

}