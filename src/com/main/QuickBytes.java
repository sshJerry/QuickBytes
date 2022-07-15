package com.main;

import java.sql.SQLException;

import com.main.service.UserService;

public class QuickBytes {
	
	public static void main(String[] args)  {
		System.out.println("****Welcome to QuickBytes Canteen Service****"); 
		
		UserService uService = new UserService();
		int mainInput;
		
		//Main Menu
		while (true) {
			mainInput = uService.displayMainAndReadInput();
			
			// Option 1 - Login
			if (mainInput == 1) {
			uService.login();
			}
			
			// Option 2 - Create Account
			if (mainInput == 2) {
				uService.createAccount(); 
			}
			
			// Option 0 - Exit
			if (mainInput == 0) {
				break;	
			}
		}
		
		System.out.println("\nExiting... Farewell!"); //exit program
	}
}
