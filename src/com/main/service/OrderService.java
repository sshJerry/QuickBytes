package com.main.service;

import java.util.List;
import java.util.Scanner;

import com.main.db.Database;
import com.main.model.Item;
import com.main.model.Order;

public class OrderService {
	Database db;
	
	//Anonymous block initializing database for CRUD methods
	{
		db = new Database();
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED AFTER CHECKING IF CUSTOMER ORDER EXISTS AT SELECTED VENDOR + NONE EXISTS
	 * create initial order
	 * @param order - Order data object we intend to create
	 * @param item - Item being added to new order
	 */
	public void createOrder(Order order, Item item) {
		db.addOrder(order);
		List<Order> orders = db.fetchOrders();
		
		//compare status, orderTime, and customerId to find matching a Order in db since missing orderId
		for (Order o : orders) {
			if (o.getStatus() == order.getStatus() && 
				o.getOrderTime() == order.getOrderTime() &&
				o.getCustomerId() == order.getCustomerId() &&
			o.getVendorId() == order.getVendorId()) {
				//add OrderItem to newly made Order
				db.addOrderItem(o, item);
			}
		}
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED AFTER CHECKING IF CUSTOMER ORDER EXISTS AT SELECTED VENDOR + EXISTS
	 * add item to existing order
	 * @param order - Order data object we are appending to
	 * @param item - Item being added to new order
	 */
	public void addItemToOrder(Order order, Item item) {
		Order newOrder = new Order(order.getOrderId(),order.getTotalPrice()+item.getPrice(),order.getStatus(),order.getOrderTime(),order.getEndTime(),order.getCustomerId(),order.getVendorId());
		
		db.updateOrder(order,newOrder);
		db.addOrderItem(order, item);
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN VENDOR ACCEPTS/DENIES AN ORDER
	 * update order status & end time
	 * @param order - Order being updated
	 * @param status - new status of Order
	 * @param endTime - new end time of Order (should have been null before)
	 */
	public void updateOrderStatus(Order order, String status, String endTime) {
		Order newOrder = order;
		newOrder.setStatus(status);
		newOrder.setEndTime(endTime);
		db.updateOrder(order, newOrder);
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN REMOVING AN ITEM FROM AN ORDER
	 * delete item from order
	 * @param order - Order in which item is being removing
	 * @param item - Item the user is removing
	 */
	public void deleteItemFromOrder(Order order, Item item) {
		db.removeOrderItem(order,item);
		
		//remove cost of item from order
		Order newOrder = order;
		newOrder.setTotalPrice(order.getTotalPrice()-item.getPrice());
		db.updateOrder(order,newOrder);
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN REMOVING ALL ITEMS FROM AN ORDER AS A CUSTOMER
	 * delete order items
	 * @param order - Order in which items are being removing
	 */
	public void deleteItemsFromOrder(Order order) {
		db.removeOrderItems(order);
	}
	
	/*
	 * KEVIN
	 * TO BE CALLED AFTER REMOVING THE LAST ITEM FROM AN ORDER AS A CUSTOMER
	 * delete order obj
	 * @param order - Order in which item is being removing
	 */
	public void deleteOrder(Order order) {
		db.removeOrder(order);
	}
}
