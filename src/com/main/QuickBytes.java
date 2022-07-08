package com.main;

import com.main.service.UserService;

public class QuickBytes {
	public static void main(String[] args) {
		UserService userService = new UserService();
		
		while (true) {
			int input = userService.displayMenuAndReadInput();
			System.out.println(input);
			
			if (input == 0) {
				System.out.println("Exiting... Bye!");
				break;
			}
			
		}
	}
}
