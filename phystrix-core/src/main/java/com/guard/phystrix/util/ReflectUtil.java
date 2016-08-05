package com.guard.phystrix.util;

public class ReflectUtil {

	
	public static Class<?>[] toTargetClazz(Object[]  objs){
		Class<?> clazz[] = new Class<?>[objs.length];
		
		for(int i=0;i<objs.length;i++){
			clazz[i] = objs.getClass();
		}
		return clazz;
	}
}
