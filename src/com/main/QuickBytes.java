package com.main;

import java.sql.SQLException;

import com.main.db.Database;
import com.main.service.UserService;

public class QuickBytes {
	public static void main(String[] args) throws SQLException {
		UserService userService = new UserService();
		int userType;
		boolean userTypeIsValid = false;
		Database db=new Database();
		db.dbConnect();
		while (!userTypeIsValid) {
			userType = userService.displayMenuAndReadInput();
			
			if (userType == 3) {
				break;
			}
			
			if (userType == 1 || userType == 2) { //if user is a customer or vendor
				//stop while loop
				userService.login(userType); //login
			} 
			if (userType==0) { 
				//stop while loop
				userService.createAccount(); 
			}
		}
		
		System.out.println("\nExiting... Farewell!"); //exit program
	}
}
