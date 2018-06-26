//package com.netcommon.executor;
//
//import com.vip.vis.stock.analysis.modules.pr.bo.TransitParamBO;
//
//import java.util.concurrent.Delayed;
//import java.util.concurrent.TimeUnit;
//
///**
//* 异步任务
//* @author bing
//* @create 2018/3/19
//* @version 1.0
//**/
//class DelayEvent implements Delayed {
//
//	public static final int DELAY_TIME = 1000;
//
//	public TransitParamBO getTransitParamBO() {
//		return transitParamBO;
//	}
//
//	public void setTransitParamBO(TransitParamBO transitParamBO) {
//		this.transitParamBO = transitParamBO;
//	}
//
//	private TransitParamBO transitParamBO;
//	private long insertTime;// 开始时间。
//
//	public DelayEvent(long insertTime, TransitParamBO transitParamBO) {
//		this.transitParamBO = transitParamBO;
//		this.insertTime = insertTime;
//	}
//
//	// 获取失效时间
//	@Override
//	public long getDelay(TimeUnit unit) {
//		// 获取失效时间
//		return this.insertTime+ DELAY_TIME - System.currentTimeMillis();
//	}
//
//	@Override
//	public int compareTo(Delayed o) {
//		// 比较 1是放入队尾 -1是放入队头
//		DelayEvent that = (DelayEvent) o;
//		if (this.insertTime > that.insertTime) {
//			return 1;
//		} else if (this.insertTime == that.insertTime) {
//			return 0;
//		} else {
//			return -1;
//		}
//	}
//}