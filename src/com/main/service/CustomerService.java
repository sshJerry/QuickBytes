package com.main.service;

import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Customer;
import com.utility.CustomerUtility;

/*
 * JERRY - Thoughts a creative and fun initializer for default balance?
 * 
 */

public class CustomerService {
	Database db;
	private Scanner sc;
	Customer customer = new Customer();
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
		sc = new Scanner(System.in);
	}
	
	// Jerry Implementation
	public void createAccount() {
		// TODO Auto-generated method stub
		//Placeholder for customer creation menu
		System.out.println("DEBUG: Customer Creating Account"); 
		db.dbConnect();
		System.out.println("DEBUG: Connectioned"); 
		System.out.println("Enter first name");
		sc.nextLine(); // leave in code
		String customerFirstName = sc.nextLine();
		System.out.println("Enter last name");
		String customerLastName = sc.nextLine();
		System.out.println("Enter desired username");
		String customerUsername = sc.nextLine();
		System.out.println("Enter desired password");
		String customerPassword = sc.nextLine();
		System.out.println("Enter your Employee ID");
		int customerEmployeeId = sc.nextInt();
		
		customer.setFirstName(customerFirstName);
		customer.setLastName(customerLastName);
		customer.setUsername(customerUsername);
		customer.setPassword(customerPassword);
		customer.setEmployeeId(customerEmployeeId);
		customer.setBalance(0);
		db.createCustomerAccount(customer);
		System.out.print(customer);
		db.dbClose();
	}
	
	public void login() {
		// TODO Auto-generated method stub
		//Placeholder for customer creation menu
		System.out.println("DEBUG: Customer Logging In");
		db.dbConnect();
		System.out.println("DEBUG: Connectioned"); 
		System.out.println("Enter your username");
		String customerLoginUsername = sc.nextLine();
		boolean isValidUsername = CustomerUtility.validateUsername(db.getAllCustomers(),customerLoginUsername);
		if(!isValidUsername) 
			System.out.println("Incorrect Credentials");

		System.out.println("Enter your password");
		String customerLoginPassword = sc.nextLine();
		boolean isValidPassword = CustomerUtility.validatePassword(db.getAllCustomers(),customerLoginPassword);
		if(!isValidPassword) 
			System.out.println("Incorrect Credentials");
		
		//Maintaining Login not added
		if(isValidUsername && isValidPassword)
			System.out.println("Signed in!");
		db.dbClose();
		
	}
	
}
