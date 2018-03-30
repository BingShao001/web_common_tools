package com.netcommon.test;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {
	int count = 50;
	//发令枪
	private CountDownLatch cdl = new CountDownLatch(count);

	@Override
	public void run() {
		try {
			// 让所有子线程在这里等待，当所有线程实例化后，同时停止等待
			cdl.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (count > 0) {
			count--;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String flag = Thread.currentThread().getName();
			if (count == 0) {
				flag += "_@@@@@";
			}
			System.out.println(flag + " : " + count);
		}
	}
	@org.junit.Test
	public void threadTest(){
		System.out.println("test...");
		for (int i = 0; i < 50; i++) {
			new MyThread().start();
			//实例化线程并减一
			cdl.countDown();
		}
	}
}
