package com.main.service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Customer;
import com.main.model.Vendor;

public class UserService {
	private Scanner sc;
	Database db = new Database();
	public int displayMenuAndReadInput() {
		int input = -1;
		
		System.out.println("****Welcome to QuickBytes Canteen Service****\n");
		System.out.println("0. Create Account");
		System.out.println("If Account is Already Created, Login As:");
		System.out.println("1. Customer");
		System.out.println("2. Vendor");
		System.out.println("3. Exit");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type. Please Try Again!\n");
			sc.next();
		}
		
		return input;
	}

	{
		this.sc = new Scanner(System.in);
	}

	public void login(int userType) throws SQLException {
		Boolean userIsValid = false;
		
		try {
			System.out.println("\nWelcome!");
			System.out.println("Please Enter Your Credentials: \n");
			System.out.print("Username: ");
			sc.nextLine();
			String username = sc.nextLine();
			System.out.print("Password: ");                          
			String password = sc.nextLine();
			
			switch(userType) {
			case 1: 
				userIsValid = db.validateCustomer(username,password);
				if (userIsValid==true) {
					System.out.println("validated");
				}
				else {
					System.out.println("not validated");
				}
				break;
			case 2: 
				userIsValid = db.validateVendor(username,password);
				break;
			default: 
				break;
			}
		} catch (InputMismatchException ime) {
			System.out.println("\n Incorrect Input Type. Please Try Again!");
		}
	}

	public void createaccount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Create Account as:");
		System.out.println("1: Customer");
		System.out.println("2: Vendor");
		Customer customer=new Customer();
		Vendor vendor=new Vendor();
		int input=sc.nextInt();
		if (input==1) {
			System.out.println("enter employeeId");
			customer.setEmployeeId(sc.nextInt());
			System.out.println("enter firstname");
			customer.setFirstName(sc.next());
			System.out.println("enter lastname");
			customer.setLastName(sc.next());
			System.out.println("enter username");
			customer.setUsername(sc.next());
			System.out.println("enter password");
			customer.setPassword(sc.next());
			System.out.println("add balance");
			customer.setBalance(sc.nextFloat());
			db.insertCustomer(customer);
		
			
		}
		switch(input) {
		case(2): 	
		System.out.println("enter businessId");
		vendor.setBusinessId(sc.nextInt());
		System.out.println("enter name");
		vendor.setName(sc.next());
		System.out.println("enter username");
		vendor.setUsername(sc.next());
		System.out.println("enter password");
		vendor.setPassword(sc.next());
		db.insertVendor(vendor);
		
		}
		
		
	}

}
