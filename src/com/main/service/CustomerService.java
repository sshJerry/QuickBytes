package com.main.service;

import java.util.Scanner;

public class CustomerService {
	Scanner sc = new Scanner(System.in);

	public void createAccount() {
		// TODO Auto-generated method stub
		
	}

	public void login() {
		// TODO Auto-generated method stub
		
	}
	
	public void addMoreItems(String customerLoginUsername, int vendorId) {
		System.out.println("Item successfully added to order!\n\n Press 1 to add more items\nPress 0 to submit your order");
		int choice = sc.nextInt();
		
		if (choice == 1) {
			//return to other function recursively
		}
		else if (choice == 0) {
			
		}
		
	}
}
