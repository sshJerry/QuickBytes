package com.main.model;

public class OrderItem {
	private int orderId;
	private int itemId;
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderItem(int orderId, int itemId) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", itemId=" + itemId + "]";
	}
	
	
}
