package com.netcommon.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) {
//		Singleton singleton = Singleton.getSingleton();
//		Singleton singleton2 = Singleton.getSingleton();
//		System.out.println(singleton == singleton2);
//		
//		Singliton singliton = Singliton.getSingliton();
//		Singliton singliton2 = Singliton.getSingliton();
//		System.out.println(singleton == singleton2);
		/**生成Class元对象**/
		Class<?> clazz = Singleton.class;
		try {
			/**动态动用无参构造器**/
			Constructor<?> c = clazz.getDeclaredConstructor(null);
			/**设置可访问权限**/
			c.setAccessible(true);
			List<String> list = new LinkedList<>();
			list.add("张");
			list.add("兵");
			System.out.println(list.get(0));
			System.out.println(JSON.toJSONString(list));
			System.out.println(100>>5);
			try {
				c.newInstance();
				c.newInstance();
				c.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
