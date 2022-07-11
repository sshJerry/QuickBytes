package com.main.model;

public class Customer {
	private int customerId;
	private int employeeId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private float balance;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerId, int employeeId, String firstName, String lastName, String username, String password,
			float balance) {
		super();
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", employeeId=" + employeeId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username + ", password=" + password + ", balance="
				+ balance + "]";
	}
	
}
