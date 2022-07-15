package com.main.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Admin;
import com.main.model.Vendor;

public class AdminService {
	Scanner sc;
	Database db;
	ReportService rs;
	
	{
		rs = new ReportService();
	}
	
	//login
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
	
	//basic account create
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
	
	//admin menu
	private int displayMenuAndReadInput() {
		int input = -1;
		
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. Generate reports for a Vendor");
		System.out.println("2. Add a new Admin");
		System.out.println("0. Return to Login/Create Account Menu");
		
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type Detected!\n");
			sc.next();
		}
		
		return input;
	}
	//admin choices
	public void chooseOption() {
		int input = displayMenuAndReadInput();
		if (input == 1) {
			chooseVendor();
		}
		else if(input == 2) {
			createAccount();
			chooseOption();
		}
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN ADMIN PRESSES 1
	 */
	public void chooseVendor() {
		int input = displayVendorMenuAndReadInput();
		
		if (input == 0) {
			chooseOption();
		} else {
			rs.generateGeneralReport(db.fetchVendor(input)); //generate report from ReportService
		}	
		
	};
	
	/*
	 * KEVIN
	 * display vendors as a menu
	 */
	public int displayVendorMenuAndReadInput() {
		int input = -1;
		boolean validInput = false;
		
		System.out.println("\nChoose a Vendor: ");
		
		List<Vendor> vendors = db.fetchVendors();
		for (Vendor v : vendors) {
			System.out.println(v.getVendorId() + ". " + v.getName());
		}
		
		System.out.println("0. Exit");
		try {
			input = sc.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("\nIncorrect Input Type Detected!\n");
			sc.next();
		}
		
		//validate if vendor exists
		for (Vendor v : vendors) {
			if (input == v.getVendorId()) {
				validInput = true;
			}
		}
		
		if (validInput) {
			return input;
		} else {
			System.out.println("\nVendor ID Not Detected!\n");
			return 0;
		}
	}
}
