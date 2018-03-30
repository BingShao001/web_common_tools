package com.netcommon.entity;

public class OrderEntity {

	private long orderId;
	private String orderName;
	private double price;
	private String saller;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSaller() {
		return saller;
	}
	public void setSaller(String saller) {
		this.saller = saller;
	}
	
}
