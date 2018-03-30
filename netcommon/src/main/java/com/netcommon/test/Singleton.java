package com.netcommon.test;

public class Singleton {

	 public static Singleton singleton = new Singleton();
	 private static boolean flag = false;
	 
	 private Singleton(){
	  synchronized (Singleton.class) {
	   if(!flag){
	    flag = !flag;
	   }else{
	    throw new RuntimeException("反射侵犯");
	   } 
	  }
	 }
	 
	 public static Singleton getSingleton(){
	  return singleton;
	 }
	}