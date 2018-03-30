package com.netcommon.test.loadbalance;

public class Main {

	public static void main(String[] args) {
		Object object= new Object();
	
		for (int i = 0; i < 100; i++) {
			ThreadTest test = new ThreadTest();
			test.start();
		}
	}
}
