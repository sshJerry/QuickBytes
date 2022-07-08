package com.main.model;

public class Item {
	private int itemId;
	public Item(int itemId, int vendorId, String name, float price, int quantity) {
		super();
		this.itemId = itemId;
		this.vendorId = vendorId;
		this.name = name;
		Price = price;
		this.quantity = quantity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private int vendorId;
	private String name;
	private float Price;
	private int quantity;
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", vendorId=" + vendorId + ", name=" + name + ", Price=" + Price
				+ ", quantity=" + quantity + "]";
	}
}
