package com.main.service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.main.db.Database;

public class UserService {
	private Scanner sc;
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

	{
		this.sc = new Scanner(System.in);
	}

	public void login(int userType) throws SQLException {
		Database db = new Database();
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

}
