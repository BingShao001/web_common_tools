package com.netcommon.test.loadbalance;

public class ThreadTest extends Thread {

	@Override
	public void run() {
		System.out.println(LoadBalanceUtils.weightBalance());
	}
}
