package com.main.model;

public class Set {
	
	private int setId;
	private String name;
	private float price;
	private int available;
	private int vendorId;

	public int getSetId() {
		return setId;
	}
	public void setSetId(int setId) {
		this.setId = setId;
	}
	public Set(String name, float price, int available, int vendorId) {
		super();
		this.name = name;
		this.price = price;
		this.available = available;
		this.vendorId = vendorId;
	}
	
	public Set(int setId, String name, float price, int available, int vendorId) {
		super();
		this.setId = setId;
		this.name = name;
		this.price = price;
		this.available = available;
		this.vendorId = vendorId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

}
