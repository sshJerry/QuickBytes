package com.main.service;

import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Item;

public class ItemService {
	Database db = new Database();
	private Scanner sc;
	
	
	//test main method
	public static void main(String[] args) {
		ItemService is = new ItemService();
		is.createItem(1);
	}
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
}
