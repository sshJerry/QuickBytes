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
				o.getCustomerId() == order.getCustomerId()) {
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
		Order newOrder = new Order(order.getOrderId(),order.getTotalPrice()+item.getPrice(),order.getStatus(),order.getOrderTime(),order.getEndTime(),order.getCustomerId());
		
		db.updateOrder(order,newOrder);
		db.addOrderItem(order, item);
	}
}
