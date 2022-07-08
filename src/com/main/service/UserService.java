package com.main.service;

import java.util.Scanner;

public class UserService {
	private Scanner sc;
	{
		this.sc = new Scanner(System.in);
	}

	public int displayMenuAndReadInput() {
		System.out.println("****QuickBytes****");
		System.out.println("Login As:");
		System.out.println("1. Employee");
		System.out.println("2. Vendor");
		System.out.println("0. Exit");
		int input = sc.nextInt();
		
		return input;
	}

	public void login(int userType) {
		if (userType == 1) {
			//customer login
		} 
		if (userType == 2){
			//vendor login
		}
	}

}
