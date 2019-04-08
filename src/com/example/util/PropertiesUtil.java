package com.example.util;

import java.util.Properties;

public class PropertiesUtil {
	public static String getValueByKey(String key) {
		Properties pro = new Properties();
		try {
			pro.load(PropertiesUtil.class.getResourceAsStream("/assets/yky.properties"));  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro.getProperty(key);

	}

}
