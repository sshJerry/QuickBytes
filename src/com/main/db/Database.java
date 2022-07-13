package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.main.model.Item;
import com.main.model.Order;
import com.main.model.Vendor;

public class Database {
	Connection con;
	
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbytesteam","root","Password123");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/** VENDOR */
	
	/*
	 * KEVIN
	 * @param vendor - Vendor obj to be inserted
	 */
	public void addVendor(Vendor vendor) {
		dbConnect();
		
		String sql="insert into vendor(businessId,name,username,password) values (?,?,?,?);";
		 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vendor.getBusinessId());
			pstmt.setString(2, vendor.getName());
			pstmt.setString(3, vendor.getUsername());
			pstmt.setString(4, vendor.getPassword());
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
	}
	
	/** VENDOR */
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN CUSTOMER ADDS THEIR FIRST ITEM FROM A VENDOR
	 * @param order - Order object to be inserted
	 */
	public void addOrder(Order order) {
		dbConnect();
		
		String sql="insert into `Order`(totalPrice,status,orderTime,endTime,customerId) values (?,?,?,?,?);";
		 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, order.getTotalPrice());
			pstmt.setString(2, order.getStatus());
			pstmt.setString(3, order.getOrderTime());
			pstmt.setString(4, order.getEndTime());
			pstmt.setInt(3, order.getCustomerId());
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
	}
	
	/*
	 * KEVIN
	 * get all existing orders
	 */
	public List<Order> fetchOrders() {
		dbConnect();
		String sql = "select * from order;";
		List<Order> list = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				int orderId = rst.getInt("orderId");
				float totalPrice = rst.getFloat("totalPrice");
				String status = rst.getString("status");
				String orderTime = rst.getString("orderTime");
				String endTime = rst.getString("endTime");
				int customerId = rst.getInt("customerId");
				
				Order o = new Order(orderId,totalPrice,status,orderTime, endTime,customerId);
				list.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return list;
	}
	
	/*
	 * KEVIN
	 * get an existing order by orderId
	 * @param orderId - orderId of the Order data object you want to fetch
	 */
	public Order fetchOrder(int orderId) {
		dbConnect();
		String sql = "select * from order where orderId=?;";
		Order order = new Order();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			ResultSet rst = pstmt.executeQuery();
			rst.next(); 
			
			Order o = new Order(orderId,rst.getFloat("totalPrice"),rst.getString("status"),rst.getString("orderTime"),rst.getString("endTime"),rst.getInt("customerId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
		return order;
	}

	/*
	 * KEVIN
	 * update an existing Order with new Order object's attributes
	 * @param order - old Order obj to be updated
	 * @param newOrder - new Order obj whose values we are updating order with
	 */
	public void updateOrder(Order order, Order newOrder) {
		dbConnect();
		String sql = "update `Order` set totalPrice=?, status=?, orderTime=?, endTime=?, customerId=? where orderId=?;";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, newOrder.getTotalPrice());
			pstmt.setString(2, newOrder.getStatus());
			pstmt.setString(3, newOrder.getOrderTime());
			pstmt.setString(4, newOrder.getEndTime());
			pstmt.setInt(5, newOrder.getCustomerId());
			pstmt.setInt(6, order.getOrderId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbClose();
	}
	
	/** ORDER_ITEM */
	
	/*
	 * KEVIN
	 * TO BE CALLED WHEN A CUSTOMER ADDS ANY ITEM FROM A VENDOR
	 * after addOrder() when applicable
	 * @param order - order from which you use orderId
	 * @param item - item from which you use itemId
	 */
	public void addOrderItem(Order order, Item item) {
		dbConnect();
		
		String sql="insert into Order_Item(orderId,itemId) values (?,?);";
		 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order.getOrderId());
			pstmt.setInt(2, item.getItemId());
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
	}
}
