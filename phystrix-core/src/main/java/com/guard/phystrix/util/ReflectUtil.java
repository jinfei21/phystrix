package com.guard.phystrix.util;

import java.lang.reflect.Field;

public class ReflectUtil {
	
	@SuppressWarnings("unchecked")
	public static Class<?>[] toTargetClazz(Object[]  objs){
		Class<?> clazz[] = new Class<?>[objs.length];
		
		for(int i=0;i<objs.length;i++){
			Class<? extends Object> cls = objs[i].getClass();
			
			Field f = null;
			try {
				f = cls.getDeclaredField("TYPE");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			if (f != null) {
				try {
					clazz[i] = (Class<? extends Object>) f.get(null);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			} else {
				clazz[i] = cls;
			}
		}
		return clazz;
	}
}
