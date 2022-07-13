package com.main.service;

import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Vendor;

public class VendorService {
	Database db;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}

	public void createAccount() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please enter your Business ID: ");
		int businessId = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Username: ");
		String username = sc.nextLine();
		
		System.out.print("Password: ");
		String password = sc.nextLine();
		
		Vendor vendor = new Vendor(businessId,name,username,password);
		db.addVendor(vendor);
	}
	
	public void login() {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		
		System.out.print("Please enter your Username: ");
		String username=sc.next();
		
		System.out.print("Password: ");
		String password=sc.next();
		
		
		boolean isValidated=db.validateVendor(username, password);
		if(isValidated) {
			System.out.println("Logged in");
		}
		else {
			System.out.println("Invalid Credentials");
		}
		System.out.println("DEBUG: Vendor Logging In");
	}

}
