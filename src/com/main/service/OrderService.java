package com.main.service;

import java.util.Scanner;

import com.main.db.Database;

public class OrderService {
	Database db;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}
	
	public void createOrder() {
		Scanner sc = new Scanner(System.in);
	}
}
