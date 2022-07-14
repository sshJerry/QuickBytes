package com.main.model;

public class Order {
	private int orderId;
	private float totalPrice;
	private String status;
	private String orderTime; //Temporary until we get datetime
	private String endTime; //Temporary until we get datetime
	private int customerId;
	private int vendorId;
	
	public Order() {
		super();
	}
	
	public Order(int orderId, float totalPrice, String status, String orderTime, String endTime, int customerId, int vendorId) {
		super();
		
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderTime = orderTime;
		this.endTime = endTime;
		this.customerId = customerId;
		this.vendorId = vendorId;
	}
	
	//Constructor without orderId for creating orders
	public Order(int customerId, float totalPrice, String status, String orderTime, String endTime, int vendorId) {
		super();
		
		this.customerId = customerId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderTime = orderTime;
		this.endTime = endTime;
		this.vendorId = vendorId;
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
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", totalPrice=" + totalPrice + ", status="
				+ status + ", orderTime=" + orderTime + ", endTime=" + endTime + "]";
	}
}
