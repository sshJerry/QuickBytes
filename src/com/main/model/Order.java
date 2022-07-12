package com.main.model;

public class Order {
	private int orderId;
	private float totalPrice;
	private String status;
	private String orderTime; //Temporary until we get datetime
	private String endTime; //Temporary until we get datetime
	private int customerId;
	
	public Order() {
		super();
	}
	
	public Order(int orderId, int customerId, float totalPrice, String status, String orderTime, String endTime) {
		super();
		
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderTime = orderTime;
		this.endTime = endTime;
		this.customerId = customerId;
	}
	
	//Constructor without orderId for creating orders
	public Order(int customerId, float totalPrice, String status, String orderTime, String endTime) {
		super();
		
		this.customerId = customerId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderTime = orderTime;
		this.endTime = endTime;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", totalPrice=" + totalPrice + ", status="
				+ status + ", orderTime=" + orderTime + ", endTime=" + endTime + "]";
	}
}
