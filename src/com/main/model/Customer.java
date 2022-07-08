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
	}
	
	public Customer(int customerId, int employeeId, String firstName, String lastName, String username, String password,
			float balance) {
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.balance = balance;
		
	}
}
