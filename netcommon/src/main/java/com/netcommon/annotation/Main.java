package com.netcommon.annotation;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("com.netcommon.annotation.MyService");
			boolean hasAnnotation = clazz.isAnnotationPresent(Bing.class);
			System.out.println(hasAnnotation);
			Bing bing = (Bing) clazz.getAnnotation(Bing.class);
			System.out.println(bing.value());
			System.out.println("______________________________________");
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				Ice ice = method.getAnnotation(Ice.class);
				System.out.println(ice.value());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
