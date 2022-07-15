package com.main.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.main.db.Database;
import com.main.model.Order;
import com.main.model.OrderItem;
import com.main.model.Vendor;

/** KEVIN */
public class ReportService {
	Database db;
	
	
	{
		db = new Database();
	}
	
//	PUT THIS IN ADMIN.JAVA DURING MERGE	
//	/*
//	 * KEVIN
//	 * TO BE CALLED WHEN ADMIN PRESSES 1
//	 */
//	public void chooseVendor() {
//		int input = displayVendorMenuAndReadInput();
//		
//		if (input == 0) {
//			chooseOption();
//		} else {
//			rs.generateGeneralReport(db.fetchVendor(input)); //generate report from ReportService
//		}	
//		
//	};
//	
//	/*
//	 * KEVIN
//	 * display vendors as a menu
//	 */
//	public int displayVendorMenuAndReadInput() {
//		int input = -1;
//		boolean validInput = false;
//		
//		System.out.println("\nChoose a Vendor: ");
//		
//		List<Vendor> vendors = db.fetchVendors();
//		for (Vendor v : vendors) {
//			System.out.println(v.getVendorId() + ". " + v.getName());
//		}
//		
//		System.out.println("0. Exit");
//		try {
//			input = sc.nextInt();
//		} catch (InputMismatchException ime) {
//			System.out.println("\nIncorrect Input Type Detected!\n");
//			sc.next();
//		}
//		
//		//validate if vendor exists
//		for (Vendor v : vendors) {
//			if (input == v.getVendorId()) {
//				validInput = true;
//			}
//		}
//		
//		if (validInput) {
//			return input;
//		} else {
//			System.out.println("\nVendor ID Not Detected!\n");
//			return 0;
//		}
//	}
	
	public void generateGeneralReport(Vendor vendor) {
		File report = new File("report.txt");
		
		//Total Profit of Vendor
		List<Order> vendorOrders = 
				db.fetchOrders().stream()
				.filter(o->o.getVendorId() == vendor.getVendorId())
				.collect(Collectors.toList()); //orders belonging to current vendor
		double totalProfit =  vendorOrders.stream().collect(Collectors.summingDouble(o->o.getTotalPrice()));
		
		//Orders Completed by Vendor
		int ordersCompleted =  vendorOrders.size();
		
		//Items Sold by Vendor
		List<OrderItem> vendorOrderItems = db.fetchOrderItems(); //all existing order items
		for (OrderItem oi : vendorOrderItems) {
			for (Order o : vendorOrders) {
				if (oi.getOrderId() != o.getOrderId()) {
					vendorOrderItems.remove(oi); //remove each order item not belonging to this vendor
				}
			}
		}
		int itemsSold = vendorOrderItems.size();
		
		//Most Popular Item of Vendor
		int mostPopularItemId = vendorOrderItems.stream()
		        .map(OrderItem::getItemId) //get all item ids
		        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) //group & count each unique id's occurrences
		        .entrySet().stream().max(Map.Entry.comparingByValue()) //find most common id
		        .map(Map.Entry::getKey).orElse(null); //extract unique id
		String mostPopularItem = db.fetchItem(mostPopularItemId).getName();
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(report));
			
			pw.write("Vendor ID: " + vendor.getVendorId() + "\n");
			pw.write("Business ID: " + vendor.getBusinessId() + "\n");
			pw.write("Vendor Name: " + vendor.getName() + "\n\n");
			pw.write("Total Profit: " + totalProfit + "\n");
			pw.write("Orders Completed: " + ordersCompleted + "\n");
			pw.write("Items Sold: " + itemsSold + "\n");
			pw.write("Most Popular Item: " + mostPopularItem + "\n");
			
			pw.close();
		} catch (IOException ioe) {
			System.out.println("Report Generation Failed.");
		}
	}
	
	//TODO: Monthly/Weekly Reports when datetime is available in Order
}
