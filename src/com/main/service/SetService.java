package com.main.service;

import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Set;

public class SetService {
	Database db;

	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}

	//jay to create meal sets
	public void createMealSet(int id){
		Scanner sc=new Scanner(System.in);

		System.out.print("Please enter the set name: ");
		String name=sc.nextLine();
		
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
		Scanner sc=new Scanner(System.in);
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
		Scanner sc=new Scanner(System.in);
		List<Set> sets=db.fetchSets(id);
		for (int i=0; i<sets.size(); i++) {
			System.out.println(sets.get(i).getName()+", ");
		}
		System.out.print("Please enter the name of the set to edit: ");
		String name=sc.nextLine();
		
		System.out.print("Please enter the new name of the set ");
		String newname=sc.nextLine();
		
		System.out.print("New Price: ");
		float price=sc.nextFloat();
		
		System.out.print("New Available Quantity: ");
		int available=sc.nextInt();
		
		int setId=db.fetchSetId(name);
		Set set=new Set(newname, price, available, id);
		db.updateItemSet(set, setId);
		addItems(newname, id);
		
	}

	
	
}
