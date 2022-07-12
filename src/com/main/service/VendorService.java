package com.main.service;

import com.main.db.Database;

public class VendorService {
	Database db;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}

	public void createAccount() {
		// TODO Auto-generated method stub
		System.out.println("DEBUG: Vendor Creating Account");
	}
	
	public void login() {
		// TODO Auto-generated method stub
		System.out.println("DEBUG: Vendor Logging In");
	}

}
