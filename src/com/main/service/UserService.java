package com.main.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {
	private Scanner sc;
	CustomerService cService = new CustomerService();
	VendorService vService = new VendorService();
	int userInput;
	
	//Anonymous block to initialize Scanner
	{
		this.sc = new Scanner(System.in);
	}

	/*
	 * KEVIN
	 * Called after initial boot up of application
	 */
	public int displayMainAndReadInput() {
		int input = -1;
		
		System.out.println("\nPlease login or create an account to proceed:");
		System.out.println("1. Login");
		System.out.println("2. Create Account");
		System.out.println("0. Exit Canteen System");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type Detected. Please Try a Number!\n");
			sc.next();
		}
		
		return input;
	}
	
	
	/*
	 * KEVIN
	 * Called after choosing to create an account on main menu
	 */
	public void createAccount() {
		while (true) {
			userInput = this.displayCreateAndReadInput();
			
			// Option 1 - Customer
			if (userInput == 1) {
				cService.createAccount(); 
			}
			
			// Option 2 - Vendor
			if (userInput == 2) {
				vService.createAccount(); 
			}
			
			// Option 0 - Exit
			break;
		}
	}
	
	/*
	 * KEVIN
	 * Called after choosing to login on main menu
	 */
	public void login() { // KEVIN
		while (true) {
			userInput = this.displayLoginAndReadInput();
	
			// Option 1 - Customer
			if (userInput == 1) {
				cService.login(); 
			}
			
			// Option 2 - Vendor
			if (userInput == 2) {
				vService.login(); 
			}
			
			// Option 0 - Exit
			break;
		}
	}

	/*
	 * KEVIN
	 * Signup Menu
	 */
	private int displayCreateAndReadInput() {
		int input = -1;
		
		System.out.println("\nPlease choose to create a Customer or Vendor account:");
		System.out.println("1. Customer");
		System.out.println("2. Vendor");
		System.out.println("0. Return to Login/Signup Menu");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type Detected. Please Try a Number!\n");
			sc.next();
		}
		
		return input;
	}

	/*
	 * KEVIN
	 * Login Menu
	 */
	private int displayLoginAndReadInput() {
		int input = -1;
		
		System.out.println("\nPlease choose to login as a Customer or Vendor:");
		System.out.println("1. Customer");
		System.out.println("2. Vendor");
		System.out.println("0. Return to Login/Signup Menu");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type. Please Try Again!\n");
			sc.next();
		}
		
		return input;
	}


}
