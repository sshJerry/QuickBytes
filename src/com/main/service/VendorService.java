package com.main.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Item;
import com.main.model.Order;
import com.main.model.Vendor;

public class VendorService {
	Database db;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}

	/*
	 * KEVIN
	 * Create Vendor Menu
	 */
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
	

	//Jay- allow vendor to login
	public void login() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Please enter your Username: ");
		String username=sc.next();
		
		System.out.println("Password: ");
		String password=sc.next();
		
		
		boolean isValidated=false;
		try {
			isValidated = db.validateVendor(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isValidated==true) {
			System.out.println("Logged in");
			displayVendorOptions(username);
		}
		else {
			System.out.println("Invalid Credentials");
		}
	}
	
	//Jay- display vendor items
	private void displayVendorOptions(String username) {
		Scanner sc=new Scanner(System.in);
		UserService userService=new UserService();
		
		System.out.println("1: Display Menu Item Options");
		System.out.println("2: Create Meal Set or Item");
		System.out.println("3: View Orders");
		System.out.println("0: Logout");
		
		try {
			int input = sc.nextInt();
			if (input==0) {
				userService.login();
			}
			switch(input) {
			
			case 1:
				displayMenuItems(username);
				break;
			
			case 2:
				
				createMealSetOrItem(username);
				break;
				
			case 3:
				
				viewOrders(username);
				break;
				
			}
			
		}
	 catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type. Please Try Again!\n");
		}
		
		
		
		
	}
//jay to view orders
	private void viewOrders(String username) {
		Scanner sc=new Scanner(System.in);
		int id=db.fetchVendorID(username);
	
		System.out.println("1: Accept Order");
		System.out.println("2: Delete Order");
		System.out.println("0: Back");
		List<Order> orders=new ArrayList<>();
		OrderService orderService=new OrderService();
		try {
		    int input = sc.nextInt();
			if (input==0) {
				displayVendorOptions(username);
			
			}
			switch(input) {
			
			case 2:
				orders=db.fetchOrders(id);
				orderService.acceptOrder(orders);
				displayVendorOptions(username);
				break;
				
			
			case 3:
				orders=db.fetchOrders(id);
				orderService.deleteOrder(orders);
				displayVendorOptions(username);
				break;
		}
			}
		catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type. Please Try Again!\n");
		}
		
	}

	//jay to allow a user to see and choose options to create a meal set or item
	private void createMealSetOrItem(String username) {
			Scanner sc=new Scanner(System.in);
			ItemService itemService=new ItemService();
			SetService setService=new SetService();
			int id=db.fetchVendorID(username);
		
			System.out.println("1: Create Meal Set");
			System.out.println("2: Create Item");
			System.out.println("0: Back");
			try {
			    int input = sc.nextInt();
				if (input==0) {
					displayVendorOptions(username);
				}
				
				switch(input){
				
				case 1:
					setService.createMealSet(id);
					displayVendorOptions(username);
					break;
				
				case 2:
					itemService.createItem(id);
					displayVendorOptions(username);
					break;
					
				}
			}
				catch (InputMismatchException ime) {
					System.out.println("\nIncorrect Input Type. Please Try Again!\n");
				}
				

	}
		

	
//jay display menu items for vendor
	public void displayMenuItems(String username) {
		ItemService itemService=new ItemService();
		SetService setService=new SetService();
		int id=db.fetchVendorID(username);
		Scanner sc=new Scanner(System.in);
		
	
		System.out.println("1: Edit Item");
		System.out.println("2: Edit Meal Set");
		System.out.println("0: Back");
		try {
		    int input = sc.nextInt();
			if (input==0) {
				itemService.editItem(id);
			}
			switch(input) {
			
			case 2:
				itemService.editItem(id);
				displayVendorOptions(username);
				break;
			
			case 3:
				setService.editMealSet(id);
				displayVendorOptions(username);
				break;
			
		}
		}
			catch (InputMismatchException ime) {
				System.out.println("\nIncorrect Input Type. Please Try Again!\n");
			}
			

}
}

	

	
