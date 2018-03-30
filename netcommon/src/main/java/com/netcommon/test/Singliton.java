package com.netcommon.test;

public class Singliton {

	public static Singliton singliton = null;
	private boolean flag = false;
	private Singliton() {
		if(!flag){
			flag = !flag;
		}else {
			throw new RuntimeException("反射窃取");
		}
		
	}

	public static Singliton getSingliton() {
		synchronized (Singliton.class) {
			if (singliton == null) {
				singliton = new Singliton();
				return singliton;
			}
			return singliton;
		}

	}
}
