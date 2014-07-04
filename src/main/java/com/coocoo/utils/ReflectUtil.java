package com.coocoo.utils;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectUtil {
	private static final Log log = LogFactory.getLog(ReflectUtil.class);   
	 public static Object getFieldValue(Object obj,String fieldName){
		 Object result = null;
		 Field field = ReflectUtil.getField(obj, fieldName);
		 if(field != null){
			 field.setAccessible(true);
			 try {
				result = field.get(obj);
			} catch (Exception e) {
				log.error("the property \"" + fieldName +"\" is not found");
			}
		 }
		 return result;
	 }
	 
	 public static void setFieldValue(Object obj, String fieldName, String fieldValue){
		 Field field = ReflectUtil.getField(obj, fieldName);
		 if(field != null){
			 try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (Exception e) {
				// TODO: handle exception
			}
		 }
	 }
	 
	 private static Field getField(Object obj, String fieldName) {
		 Field field = null;
		 for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()){
			 try {
				 field = clazz.getDeclaredField(fieldName);
				 break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		 }
			 return field;
	}

} 

