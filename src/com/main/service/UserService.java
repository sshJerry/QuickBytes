package com.main.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {
	private Scanner sc;
	{
		this.sc = new Scanner(System.in);
	}

	public int displayMenuAndReadInput() {
		int input = -1;
		
		System.out.println("****Welcome to QuickBytes Canteen Service****\n");
		System.out.println("Login As:");
		System.out.println("1. Customer");
		System.out.println("2. Vendor");
		System.out.println("0. Exit");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type. Please Try Again!\n");
			sc.next();
		}
		
		return input;
	}

	public void login(int userType) {
		
		try {
			System.out.println("\nWelcome!");
			System.out.println("Please Enter Your Credentials: \n");
			System.out.print("Username: ");
			sc.nextLine();
			String username = sc.nextLine();
			System.out.print("Password: ");
			String password = sc.nextLine();
		} catch (InputMismatchException ime) {
			System.out.println("\n Incorrect Input Type. Please Try Again!");
		}
		
		switch(userType) {
		case 1: 
			//validateCustomer();
			break;
		case 2: 
			//validateVendor();
			break;
		default: 
			break;
		}
	}

}
