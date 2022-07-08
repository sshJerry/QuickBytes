package com.main;

import com.main.service.UserService;

public class QuickBytes {
	public static void main(String[] args) {
		UserService userService = new UserService();
		int userType;
		boolean userTypeIsValid = false;
		
		while (!userTypeIsValid) {
			userType = userService.displayMenuAndReadInput();
			
			if (userType == 0) {
				break;
			}
			
			if (userType == 1 || userType == 2) { //if user is a customer or vendor
				userTypeIsValid = true; //stop while loop
				userService.login(userType); //login
			} 
		}
		
		System.out.println("\nExiting... Farewell!"); //exit program
	}
}
