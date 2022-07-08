package com.main;

import com.main.service.UserService;

public class QuickBytes {
	public static void main(String[] args) {
		UserService userService = new UserService();
		
		while (true) {
			int userType = userService.displayMenuAndReadInput();
			
			System.out.println(userType);
			
			if (userType == 0) {
				System.out.println("Exiting... Bye!");
				break;
			}
			
			userService.login(userType);		
			System.out.println();
			
		}
	}
}
