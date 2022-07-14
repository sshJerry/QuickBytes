package com.main.service;

import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Set;

public class SetService {
	Database db;
	private Scanner sc;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}

	//jay to create meal sets
	public void createMealSet(int id){
		

		System.out.print("Please enter the set name: ");
		String name=sc.next();
		
		System.out.print("Price: ");
		float price=sc.nextFloat();
		
		System.out.print("Available: ");
		int available=sc.nextInt();
		
		
		Set set=new Set(name, price, available, id);
		db.insertSet(set);
		addItems(name, id);
		
	}

	//jay to add items
	public void addItems(String name, int id) {
		while(true) {
		System.out.print("1: Add New Item: ");
		System.out.print("0: Exit: ");
		int input=sc.nextInt();
		if (input==0) {
			break;
		}
		switch(input) {
		case 1:
			System.out.print("1: Enter Item Name: ");
			String itemname= sc.next();
			int itemId=db.fetchItemID(itemname, id);
			int setId=db.fetchSetId(name);
			db.addItemToItemSet(itemId, setId);
			
		}
		}
		
	}

	//jay to edit meal set
	public void editMealSet(int id) {
		System.out.print("Please enter the item name: ");
		String name=sc.next();
		
		System.out.print("Price: ");
		float price=sc.nextFloat();
		
		System.out.print("Available: ");
		int available=sc.nextInt();
		
		Set set=new Set(name, price, available, id);
		db.updateItemSet(set);
		addItems(name, id);
		
	}

	
	
}
