package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Item;

public class ItemService {
	Database db = new Database();
	private Scanner sc;
	
	
	//test main method
//	public static void main(String[] args) {
//		ItemService is = new ItemService();
//		is.editItem(1);
//	}
	/*
	 * @parameters 
	 * vendorId will be taken from the current session as an input to this method 
	 */
	public void createItem(int vendorId) {
		//reading in the item specifics
		sc = new Scanner(System.in);
		Item i = new Item();
		System.out.println("Input your item name below:");
		String name= sc.nextLine();
		System.out.println("Input the price of your item:");
		float price= sc.nextFloat();
		System.out.println("How many of this product do you have in your inventory?");
		int quantity= sc.nextInt();
		
		//setting the values for the item before sending this to db
		i.setName(name);
		i.setPrice(price);
		i.setQuantity(quantity);
		i.setVendorId(vendorId);
		
		db.addItem(i);
	}
	
	/*
	 * @parameters 
	 * vendorId will taken from the session where the vendor is logged in
	 */
	
	public void editItem(int vendorId) {
		sc = new Scanner(System.in);
		
		//validating the vendors ability to edit the item
		List<Item> il= db.fetchItems(vendorId);
		for(int i = 0; i < il.size(); i++) {
			System.out.println("press "+i+" to edit "+il.get(i).getName());
		}
		int index = sc.nextInt();
		Item editedItem = il.get(index);
		int itemId = editedItem.getItemId();
		System.out.println("What would you like the new name of your item: "+ editedItem.getName()+" to be?");
		sc.nextLine();
		String newName= sc.nextLine();
		System.out.println("What would you like the new price of your item:"+ newName+" to be?");
		float price= sc.nextFloat();
		System.out.println("How many "+ newName+"s do you have in stock?");
		int quantity= sc.nextInt();
			
		//setting up the item for db editing
		Item i = new Item();
		i.setItemId(itemId);
		i.setName(newName);
		i.setQuantity(quantity);
		i.setPrice(price);
		i.setVendorId(vendorId);
		db.updateItem(i);
	}


}
