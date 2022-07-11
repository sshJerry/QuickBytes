package com.main.service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Customer;
import com.main.model.Item;
import com.main.model.Vendor;

public class UserService {
	private Scanner sc;
	Database db = new Database();
	public int displayMenuAndReadInput() {
		int input = -1;
		
		System.out.println("****Welcome to QuickBytes Canteen Service****\n"); //
		System.out.println("0. Create Account");
		System.out.println("If Account is Already Created, Login As:");
		System.out.println("1. Login As Customer");
		System.out.println("2. Login As Vendor");
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
					this.displayCustomerOptions();
				}
				else {
					System.out.println("not validated");
				}
				break;
			case 2: 
				userIsValid = db.validateVendor(username,password);
				if (userIsValid==true) {
					System.out.println("validated");
					displayVendorOptions(username);
				}
				else {
					System.out.println("not validated");
				}
				break;
			default: 
				break;
			}
		} catch (InputMismatchException ime) {
			System.out.println("\n Incorrect Input Type. Please Try Again!");
		}
	}

	public void displayVendorOptions(String username) {
		Scanner sc = new Scanner(System.in);
		Item item=new Item();
		System.out.println("1: Show Menu Items");
		System.out.println("2: Add Item");
		System.out.println("3: Create Meal Set");
		System.out.println("4: View Orders");
		System.out.println("0: Logout");
		int input=sc.nextInt();
		int id=db.fetchID(username);
		if (input==1) {
		List<Item> vlist=db.fetchItems(id);
		for (Item i: vlist) {
			System.out.println(i.getName());
	}
		}
		switch(input) {
		case(2):
			item.setVendorId(id);
			System.out.println("Enter name");
			item.setName(sc.next());
			System.out.println("Enter Price");
			item.setPrice(sc.nextFloat());
			System.out.println("Enter Quantity");
			item.setQuantity(sc.nextInt());
			db.insertItem(item);
			}}
		

	public void displayCustomerOptions() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1: Show Vendors");
		System.out.println("2: Show Order");
		System.out.println("0: Logout");
		int input=sc.nextInt();
		if (input==1) {
			List<Vendor> vlist=db.fetchVendors();
			for (Vendor v: vlist) {
				System.out.println(v.getName());
			}
		}
		
	}

	public void createAccount() {
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
