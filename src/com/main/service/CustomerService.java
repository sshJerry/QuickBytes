package com.main.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Customer;
import com.main.model.Item;
import com.main.model.Order;
import com.main.model.Vendor;
import com.utility.CustomerUtility;

/*
 * JERRY - Thoughts a creative and fun initializer for default balance?
 * 
 */

public class CustomerService {
	Database db;
	private Scanner sc;
	Customer customer = new Customer();
	OrderService os;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
		sc = new Scanner(System.in);

		
	}
	
	// Jerry Implementation
	public void createAccount() {
		db.dbConnect();
		System.out.println("Enter first name");
		sc.nextLine();
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
				loggedCustomerMainMenu(customerLoginUsername);
				break;
			}
		}
		// db.dbClose();  WHERE WILL IT CLOSE??
	}
	
	/*
	 * Think about program flow, want to call to return method and
	 * while(true) +break from this point on?
	 * Call to a voided method and reach an end point?
	 * 
	 * It'll be necessary to pass along throughout program flow,
	 * the username 
	 * */
	
	/*
	 * Issues:
	 * 		- 
	 * */
	void loggedCustomerMainMenu(String customerLoginUsername) {
		int loggedCustomerMainMenuInput = -1;
		System.out.println("\n****Main Menu****");
		System.out.println("Press the number corresponding to the number");
		System.out.println("1: List of Vendors");		
		System.out.println("2: View Balance");
		System.out.println("0: Logout");
		
		try {
			loggedCustomerMainMenuInput = sc.nextInt();
			switch(loggedCustomerMainMenuInput) {
			case 1:
				loggedCustomerVendorList(customerLoginUsername);
				break;
			case 2:
				loggedCustomerViewBalance(customerLoginUsername);
				break;
			case 0: // LOGOUT TO FIRST MENU
				break;
			default:
				System.out.println("Invalid Input, Try again");
			}
				
		}catch(InputMismatchException i){
			i.printStackTrace();
		}
	}
	
	void loggedCustomerVendorList(String customerLoginUsername) {
		int loggedCustomerVendorListInput;
		System.out.println("\n****Vendor List****");
		System.out.println("Press the number corresponding to desired vendor");
		List<Vendor> venderlist = db.fetchVendors();
		int counter =1;
		for(Vendor v:venderlist) {
			System.out.println(counter + ": " +v.getName());
			counter++;
		}
		loggedCustomerVendorListInput = sc.nextInt();
		loggedCustomerVendorListInput--;
		loggedCustomerListItemsFromVendor(customerLoginUsername, loggedCustomerVendorListInput, true);
	}
	void loggedCustomerListItemsFromVendor(String customerLoginUsername, int loggedCustomerVendorListInput, boolean firstPass) {
		System.out.println("\n****Item List****");
		System.out.println("Press the number corresponding to desired vendor");
		//System.out.println("0: Back");
		List<Item> il= db.fetchItems(loggedCustomerVendorListInput);
		for(int i = 1; i < il.size(); i++) {
			System.out.println(i+": "+il.get(i).getName() + "\t Price: " + il.get(i).getPrice());
		}
		int iId = sc.nextInt();
		Order itemO = new Order();
		Item itemI = db.fetchItem(il.get(iId-1).getItemId());
		OrderService itemOS = new OrderService();
		if (firstPass == true){
			itemOS.createOrder(itemO, itemI);
			firstPass = false;
		}
		else {
			itemOS.addItemToOrder(itemO, itemI);
		}
		loggedCustomerListItemsFromVendorAddItems(customerLoginUsername,iId,itemO,firstPass);
		
	}
	void loggedCustomerListItemsFromVendorAddItems(String customerLoginUsername, int vendorId, Order order, boolean firstPass) {
		System.out.println("Item successfully added to order!\n\n Press 1 to add more items\nPress 0 to submit your order");
		int choice = sc.nextInt();
		
		if (choice == 1) {
			//return to other function recursively
			return;
		}
		else if (choice == 0) {
			OrderService os = new OrderService();
			os.updateOrderStatus(order,"pending", "later");
		}
		else {
			System.out.println("that was not an option, please choose either 1 or 0");
			loggedCustomerListItemsFromVendorAddItems(customerLoginUsername,vendorId,order,firstPass);
		}
	}
	
	/*
	 * Issues:
	 * 		- Back to Main Menu functionality missing
	 * */
	void loggedCustomerViewBalance(String customerLoginUsername) {
		Customer logged =  db.getCustomer(customerLoginUsername);
		int loggedCustomerViewBalanceInput;
		System.out.println("\n****View Balance****");
		System.out.println("\nUser: " + customerLoginUsername +
				"\tYour current balance is: " + logged.getBalance() + "\n");
		System.out.println("Press the number corresponding to the desired action");
		System.out.println("1: Add to balance");
		System.out.println("0: Back to Main Menu");

		
		try {
			loggedCustomerViewBalanceInput = sc.nextInt();
			switch(loggedCustomerViewBalanceInput) {
			case 0:
				return;
			case 1:
				loggedCustomerAddBalance(customerLoginUsername);
				break;
			default:
				break;
			}
		}catch(InputMismatchException i){
			i.printStackTrace();
		}
	}
	void loggedCustomerAddBalance(String customerLoginUsername) {
		Customer logged =  db.getCustomer(customerLoginUsername);
		System.out.println("How much would you like to add?");
		float customerAddBalance = sc.nextFloat();
		logged.setBalance(customerAddBalance + logged.getBalance());
		System.out.println("You've added $" + customerAddBalance + " to your balance");
		System.out.println("Your balance is now: " + logged.getBalance());
		// JUMP BACK TO <loggedCustomerMainMenu>
	}
	
}
