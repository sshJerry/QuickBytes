package com.main.service;

import java.util.Scanner;

import com.main.db.Database;

public class CustomerService {
	Database db;
	private Scanner sc;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}
	
	public void createAccount() {
		// TODO Auto-generated method stub
		//Placeholder for customer creation menu
		System.out.println("DEBUG: Customer Creating Account"); 
		db.dbConnect();
		
		db.dbClose();
		
		
	}
	
	public void login() {
		// TODO Auto-generated method stub
		//Placeholder for customer creation menu
		System.out.println("DEBUG: Customer Logging In");
	}
	
}
