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
		db.dbConnect();
		while(true) {
			System.out.println("Enter your username");
			String customerLoginUsername = sc.nextLine();
			boolean isValidUsername = CustomerUtility.validateUsername(db.getAllCustomers(),customerLoginUsername);
			
			System.out.println("Enter your password");
			String customerLoginPassword = sc.nextLine();
			boolean isValidPassword = CustomerUtility.validatePassword(db.getAllCustomers(),customerLoginPassword, customerLoginUsername);
			if(!isValidPassword || !isValidUsername) {
				System.out.println("Incorrect Credentials");
			}
			
			if(isValidUsername && isValidPassword) {
				System.out.println("Signed in!");
				break;
			}
		}
		db.dbClose();
	}
	
	void loggedCustomerMainMenu() {
		/*
		 * Think about program flow, want to call to return method and
		 * while(true) +break from this point on?
		 * Call to a voided method and reach an end point?
		 * 
		 * It'll be necessary to pass along throughout program flow,
		 * the username 
		 * */
		
		//scanner
		System.out.println("Press the number corresponding to the number");
		System.out.println("1: List of Vendors");		
		System.out.println("2: View Balance");
		System.out.println("0: Logout");
		
		//Try
			//switch case for input
			//EX case 1: loggedCustomerVendorList(username);
			//EX case 2: loggedCustomerViewBalance(username);
			//EX case 0: displayMainAndReadInput() or Break;
		
		//Catch
			//Exception:
	}
	void loggedCustomerVendorList() {
		System.out.println("Press the number corresponding to the number");
		// FetchAllVendors, might have to be created
		// Pass the index of Vendor picked
		// Have a scanner int here. Store in var
	}
	void loggedCustomerVendorItemList() {
		System.out.println("Press the number corresponding to the number");
		// This Method has a parameter for var.
		// 		Pass var in List<Vendor?> sumthin = db.fetchItems(var)
		//			When item is picked ->
	}
	void loggedCustomerViewBalance() {// Parameter username
		System.out.println("Press the number corresponding to the number");
		System.out.println("1: Added to balance");
		System.out.println("0: Go back");
		//Try
			//switch case for input
			//EX case 1: loggedCustomerAddBalance(username);
			//EX case 0: displayMainAndReadInput() or Break;
	
		//Catch
			//Exception:
	}
	void loggedCustomerAddBalance() {// Parameter Username
	};
	
}
