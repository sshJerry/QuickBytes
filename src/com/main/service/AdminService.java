package com.main.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Admin;

public class AdminService {
	Scanner sc;
	Database db;
	
	public void login() {
		sc= new Scanner(System.in);
		db = new Database();
		
		System.out.print("Please enter your Username: ");
		String username=sc.next();
		
		System.out.print("Password: ");
		String password=sc.next();
		
		
		boolean isValidated=db.validateAdmin(username, password);
		if(isValidated) {
			System.out.println("Logged in");
			chooseOption();
		}
		else {
			System.out.println("Invalid Credentials, try again");
		}
	}
	
	public void createAccount() {
		sc = new Scanner(System.in);
		
		
		System.out.print("Please Enter The New Admin's First Name: ");
		String firstName = sc.next();
		
		System.out.println("Last Name: ");
		String lastName = sc.next();
		
		System.out.print("Username: ");
		String username = sc.next();
		
		System.out.print("Password: ");
		String password = sc.next();
		
		Admin admin = new Admin(firstName,lastName,username,password);
		db.addAdmin(admin);
	}
	
	private int displayMenuAndReadInput() {
		int input = -1;
		
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. Generate reports for a Vendor");
		System.out.println("2. Add a new Admin");
		System.out.println("0. Return to Role Selection Menu");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type Detected!\n");
			sc.next();
		}
		
		return input;
	}
	
	public void chooseOption() {
		int input = displayMenuAndReadInput();
		if (input == 1) {
			//this is where the generate report method will go
			chooseOption();
		}
		else if(input == 2) {
			createAccount();
			chooseOption();
		}
	}
}
