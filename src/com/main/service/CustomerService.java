package com.main.service;

import com.main.db.Database;

public class CustomerService {
	Database db;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}
	
	public void createAccount() {
		// TODO Auto-generated method stub
		System.out.println("DEBUG: Customer Creating Account");
	}
	
	public void login() {
		// TODO Auto-generated method stub
		System.out.println("DEBUG: Customer Logging In");
	}
	
}
