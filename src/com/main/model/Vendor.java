package com.main.model;

public class Vendor {
	private int vendorId;
	private int businessId;
	private String name;
	public int getVendorId() {
		return vendorId;
	}
	public Vendor() {
		super();
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public int getBusinessId() {
		return businessId;
	}
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", businessId=" + businessId + ", name=" + name + ", username="
				+ username + ", password=" + password + "]";
	}
	public Vendor(int vendorId, int businessId, String name, String username, String password) {
		super();
		this.vendorId = vendorId;
		this.businessId = businessId;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String username;
	private String password;
}
