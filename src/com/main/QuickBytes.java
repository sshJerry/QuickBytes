package com.main;

import com.main.db.Database;
import com.main.service.UserService;

public class QuickBytes {
	public static void main(String[] args) {
		UserService userService = new UserService();
		int userType;
		boolean userTypeIsValid = false;
		Database db=new Database();
		db.dbConnect();
		
		while (!userTypeIsValid) {
			userType = userService.displayMenuAndReadInput();
			
			// KEVIN
			
			// Option 1 - Login
			userService.login(userType); //login
			// Option 2 - Create Account
			userService.createAccount(); 
			// Option 0 - Exit
			
		}
		System.out.println("\nExiting... Farewell!"); //exit program
		db.close();
	}
}
