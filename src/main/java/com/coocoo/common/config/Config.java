package com.coocoo.common.config;

import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
/**
 * 获取资源配置
 * @author adam
 *
 */
public class Config {
		
	private static PropertiesConfiguration pc = PropertiesFactory.getInstance();
	
	public static Object getProperty(String key){
		return pc.getProperty(key);
	}
	
	public static List<Object> getProperties(String key){
		return pc.getList(key);
	}
}
